package org.wso2.am.integration.tests.tenantSharing;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.wso2.am.admin.clients.client.utils.AuthenticateStub;
import org.wso2.am.integration.clients.admin.api.dto.KeyManagerInfoDTO;
import org.wso2.am.integration.clients.admin.api.dto.KeyManagerListDTO;
import org.wso2.am.integration.test.impl.RestAPIAdminImpl;
import org.wso2.am.integration.test.utils.base.APIMIntegrationBaseTest;
import org.wso2.carbon.tenant.mgt.stub.TenantMgtAdminServiceStub;
import org.wso2.carbon.tenant.mgt.stub.beans.xsd.TenantInfoBean;

public class TenantSharingWithIS7TestCase extends APIMIntegrationBaseTest {

    private static final Log log = LogFactory.getLog(TenantSharingWithIS7TestCase.class);
    private static final String TEST_TENANT_DOMAIN = "test.com";
    private static final String TEST_TENANT_ADMIN = "admin@test.com";
    private static final String TEST_TENANT_ADMIN_PASSWORD = "admin123";
    private static final String TENANT_MGT_ADMIN_SERVICE = "TenantMgtAdminService";
    private RestAPIAdminImpl restAPIAdmin;

    @BeforeClass(alwaysRun = true)
    public void setEnvironment() throws Exception {
        super.init();
    }

    @Test(groups = {"wso2.am"}, description = "Test tenant creation by the payload used by IS7 tenant sharing " +
            "scenario and configuration of Resident key manager by default for the created tenant")
    public void testTenantCreationAndKeyManagerConfig() throws Exception {
        // Create TenantInfoBean
        TenantInfoBean tenantInfoBean = new TenantInfoBean();
        tenantInfoBean.setTenantDomain(TEST_TENANT_DOMAIN);
        tenantInfoBean.setAdmin(TEST_TENANT_ADMIN);
        tenantInfoBean.setFirstname("Test");
        tenantInfoBean.setLastname("Admin");
        tenantInfoBean.setAdminPassword(TEST_TENANT_ADMIN_PASSWORD);
        tenantInfoBean.setEmail("admin@test.com");

        String backendURL = publisherContext.getContextUrls().getBackEndUrl();
        String tenantMgtAdminServiceURL = backendURL.endsWith("/") ? backendURL + TENANT_MGT_ADMIN_SERVICE :
                backendURL + "/" + TENANT_MGT_ADMIN_SERVICE;
        log.info("Tenant Management Admin Service URL: " + tenantMgtAdminServiceURL);
        try {
            TenantMgtAdminServiceStub tenantMgtAdminServiceStub =
                    new TenantMgtAdminServiceStub(tenantMgtAdminServiceURL);

            AuthenticateStub
                    .authenticateStub(publisherContext.getSuperTenant().getTenantAdmin().getUserName(),
                            publisherContext.getSuperTenant().getTenantAdmin().getPassword(),
                            tenantMgtAdminServiceStub);

            log.info("Tenant is ready to be created by the user: " +
                    publisherContext.getSuperTenant().getTenantAdmin().getUserName());
            // Trigger tenant creation
            tenantMgtAdminServiceStub.addTenant(tenantInfoBean);

            // Wait for tenant creation and key manager configuration
            Thread.sleep(5000);

            // Initialize REST API admin client
            restAPIAdmin = new RestAPIAdminImpl(TEST_TENANT_ADMIN, TEST_TENANT_ADMIN_PASSWORD,
                    TEST_TENANT_DOMAIN, publisherURLHttps);

            // Verify Key Manager configuration
            KeyManagerListDTO keyManagers = restAPIAdmin.getKeyManagers();
            boolean hasResidentKeyManager = false;

            Assert.assertNotNull(keyManagers,
                    "Key Managers list cannot be null after tenant creation");

            Assert.assertTrue(keyManagers.getCount() > 0,
                    "Key Managers count should be greater than zero after tenant creation");

            for (KeyManagerInfoDTO keyManager : keyManagers.getList()) {
                if ("Resident Key Manager".equals(keyManager.getName())) {
                    hasResidentKeyManager = true;
                    break;
                }
            }

            Assert.assertTrue(hasResidentKeyManager,
                    "Resident Key Manager was not automatically configured for the tenant");

        } catch (Exception e) {
            log.error("Error while testing tenant creation and key manager configuration", e);
            throw e;
        }
    }

    @AfterClass(alwaysRun = true)
    public void cleanUp() throws Exception {
        String backendURL = publisherContext.getContextUrls().getBackEndUrl();
        // Clean up the created tenant
        try {
            TenantMgtAdminServiceStub tenantMgtAdminServiceStub = new TenantMgtAdminServiceStub(backendURL
                    + TENANT_MGT_ADMIN_SERVICE);
            tenantMgtAdminServiceStub.deleteTenant(TEST_TENANT_DOMAIN);
        } catch (Exception e) {
            log.error("Error while cleaning up test tenant", e);
        }
    }
}