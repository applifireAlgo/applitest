package com.app.server.service.appbasicsetup.userrolemanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.appbasicsetup.userrolemanagement.RolesRepository;
import com.app.shared.appbasicsetup.userrolemanagement.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;
import com.athena.server.pluggable.utils.helper.EntityValidatorHelper;
import com.app.server.service.RandomValueGenerator;
import java.util.HashMap;
import java.util.List;
import com.spartan.healthmeter.entity.scheduler.ArtMethodCallStack;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.After;
import org.springframework.mock.web.MockServletContext;
import com.spartan.pluggable.logger.api.LogManagerFactory;
import com.athena.server.pluggable.utils.AppLoggerConstant;
import com.spartan.pluggable.logger.api.LogManager;
import com.spartan.pluggable.logger.event.RequestHeaderBean;
import com.spartan.pluggable.logger.api.RuntimeLogUserInfoBean;
import com.athena.server.pluggable.interfaces.CommonEntityInterface.RECORD_TYPE;
import org.junit.Test;
import com.app.shared.appbasicsetup.userrolemanagement.RoleMenuBridge;
import com.app.shared.appbasicsetup.userrolemanagement.AppMenus;
import com.app.server.repository.appbasicsetup.userrolemanagement.AppMenusRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class RolesTestCase extends EntityTestCriteria {

    @Autowired
    private RolesRepository<Roles> rolesRepository;

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Autowired
    private EntityValidatorHelper<Object> entityValidator;

    private RandomValueGenerator valueGenerator = new RandomValueGenerator();

    private static HashMap<String, Object> map = new HashMap<String, Object>();

    private static List<EntityTestCriteria> entityContraint;

    @Autowired
    private ArtMethodCallStack methodCallStack;

    protected MockHttpSession session;

    protected MockHttpServletRequest request;

    protected MockHttpServletResponse response;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        MockServletContext mockServletContext = new MockServletContext("file:src/main/webapp");
        try {
            String _path = mockServletContext.getRealPath("/WEB-INF/conf/");
            LogManagerFactory.createLogManager(_path, AppLoggerConstant.LOGGER_ID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void startSession() {
        session = new MockHttpSession();
    }

    protected void endSession() {
        session.clearAttributes();
        session.invalidate();
        session = null;
    }

    protected void startRequest() {
        request = new MockHttpServletRequest();
        request.setSession(session);
        org.springframework.web.context.request.RequestContextHolder.setRequestAttributes(new org.springframework.web.context.request.ServletRequestAttributes(request));
    }

    protected void endRequest() {
        ((org.springframework.web.context.request.ServletRequestAttributes) org.springframework.web.context.request.RequestContextHolder.getRequestAttributes()).requestCompleted();
        org.springframework.web.context.request.RequestContextHolder.resetRequestAttributes();
        request = null;
    }

    @Before
    public void before() {
        startSession();
        startRequest();
        setBeans();
    }

    @After
    public void after() {
        endSession();
        endRequest();
    }

    private void setBeans() {
        runtimeLogInfoHelper.createRuntimeLogUserInfo("customer", "AAAAA", request.getRemoteHost());
        org.junit.Assert.assertNotNull(runtimeLogInfoHelper);
        methodCallStack.setRequestId(java.util.UUID.randomUUID().toString().toUpperCase());
        entityContraint = addingListOfFieldForNegativeTesting();
        runtimeLogInfoHelper.setRequestHeaderBean(new RequestHeaderBean(new RuntimeLogUserInfoBean("AAAA", "AAAA", request.getRemoteHost(), 0, 0, 0), "", methodCallStack.getRequestId()));
    }

    private Roles createRoles(Boolean isSave) throws Exception {
        Roles roles = new Roles();
        roles.setRoleHelp("THWHu8VvSsaU51pUIVhqaXMrYBF8omLTIWQfEo3u0KZVARJvs5");
        roles.setRoleIcon("v8k9Duw8Ntqz1GXQSZ91n8NVGNxifeq7D7MnWmje84GMrEB26h");
        roles.setRoleDescription("3D78Kh1tQ4MVT00yYeT8CL5tVtNDOmHuhaZjmbNntXEhwr6m1d");
        roles.setRoleName("asNanEZovDnriADW27Qt58MQ90JUw48acy6S8DicJd8aDupmsE");
        java.util.List<RoleMenuBridge> listOfRoleMenuBridge = new java.util.ArrayList<RoleMenuBridge>();
        RoleMenuBridge rolemenubridge = new RoleMenuBridge();
        rolemenubridge.setIsRead(true);
        AppMenus appmenus = new AppMenus();
        appmenus.setUiType("GMo");
        appmenus.setRefObjectId("wdiSONlvpGzBaQHJIL0FFIPHo8ezs1thq0DdPQnZcQP9B0c5Ka");
        appmenus.setMenuDisplay(true);
        appmenus.setMenuLabel("WYMIgg55spxcpQ0k1uBKJDlGCX5uFoUvstUASp8KK4kSj3mbhn");
        appmenus.setAppId("3Rn0pZG7NFYRpAziDTgxgawRyYBhbTTxhh6ISrubrKlZlpkbqa");
        appmenus.setAppType(2);
        appmenus.setAutoSave(true);
        appmenus.setMenuAction("WTUFkLFrhj4NXaEddAxiuaJsUNXydYlALA7tZICFNmooCKRhub");
        appmenus.setMenuCommands("g2Q2BGXV5notrmM2c01SZbYVg4xs4GwbsAERABhrAJBHv0JNIt");
        appmenus.setMenuAccessRights(2);
        appmenus.setMenuIcon("ejlc6snyika3n5Xo4jUBlY0wxzZpgsCedFqt8Iuut1WD7aepoU");
        appmenus.setMenuHead(true);
        appmenus.setMenuTreeId("0pnauE9NR6uub3f9tR6cZtQ5OxkuN63P1ULfbEdzriGie4Qn8i");
        AppMenus AppMenusTest = new AppMenus();
        if (isSave) {
            AppMenusTest = appmenusRepository.save(appmenus);
            map.put("AppMenusPrimaryKey", appmenus._getPrimarykey());
        }
        rolemenubridge.setIsRead(true);
        rolemenubridge.setRoles(roles);
        rolemenubridge.setIsExecute(true);
        rolemenubridge.setIsWrite(true);
        rolemenubridge.setMenuId((java.lang.String) AppMenusTest._getPrimarykey());
        listOfRoleMenuBridge.add(rolemenubridge);
        roles.addAllRoleMenuBridge(listOfRoleMenuBridge);
        roles.setEntityValidator(entityValidator);
        return roles;
    }

    @Test
    public void test1Save() {
        try {
            Roles roles = createRoles(true);
            roles.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            roles.isValid();
            rolesRepository.save(roles);
            map.put("RolesPrimaryKey", roles._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private AppMenusRepository<AppMenus> appmenusRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("RolesPrimaryKey"));
            Roles roles = rolesRepository.findById((java.lang.String) map.get("RolesPrimaryKey"));
            roles.setRoleHelp("DMW1kQef4Sfofdpp7nUT5NvaSzep2XRzO88B6UUe7RpAMWx1Rz");
            roles.setRoleIcon("X5KfrTC5UDLYK5VVmm8NGJo7cKVQUVHmIrwAjaRloMuSNnBlZA");
            roles.setVersionId(1);
            roles.setRoleDescription("AsX1wR5x0TUX8zOLvCkpQiRE6uSIs2pbxAeVjy5p7r5UBy2mkP");
            roles.setRoleName("bHSKUTtyzYVcpJfnBdtPX2ZtIXWKIOW8gtXMKDNkCcCTWukWf0");
            roles.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            rolesRepository.update(roles);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("RolesPrimaryKey"));
            rolesRepository.findById((java.lang.String) map.get("RolesPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("RolesPrimaryKey"));
            rolesRepository.delete((java.lang.String) map.get("RolesPrimaryKey")); /* Deleting refrenced data */
            appmenusRepository.delete((java.lang.String) map.get("AppMenusPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateRoles(EntityTestCriteria contraints, Roles roles) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            roles.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            roles.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            roles.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            rolesRepository.save(roles);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "RoleName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "roleName", "0wESjSYQr6E4R90Pn3vwRDXgrxJnmKJrqQXWoRFXuSljRkA4jpgrvdiKqo6KHC1jvigaDQXjHjOz3sGoyTPo3cHYA81RVIOPc6YKsXU8MwoSXjPXFADjGqcmwvbKHmuj0ThqtIPFYxBT6bPCmqJ8B7YbnSpghsDotaOkEiVtLjKLdp8UjEqXOwHlgE8nlvdgtfp7Baeq2RnuDHsXBVb5z62xFfv7DKhdTJnFRrd3ugZvNvZ86RcYxMDKjYY62grFw"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "RoleDescription", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "roleDescription", "xGAu1QdRYqe2elA5DnyZehAiHp0JrKWjPLOiupZmbFyDUeUdmRlPa8UDRNKW4s5UmAzMWOq7xHFLx0hakknNKQQ3dB9Q4HaJrQ9xsVbqBHEZubm52Cga7lYkR0pP34qxhauP4qhrFUkTJBZMr2vuqHQrgCz9DJGBadR0kt28RV5YYatORIibC4Wcn8kvUjMtLuejFyfX23BrzKXGJP5aFsX6R82LCc7kvCx6BNLYJ57bcj8ME5wmH1XaNGIWkETCS"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "roleIcon", "onXEcyP3ANCE1jIPcXzC7rfT5SfO14LP63bsHdETKDSaPDhnth1988QxppS7GetIv"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "roleHelp", "LPMoGOhp1B8lUkUMYGew1Lm6oJ1Pt4aj63kn63XuTZicZNy5qJ5a9CQFIkiF4o2BcLMEfmqz1HkbBZsZ3ddbgzdLTmVPpaIbyItGXQH8xTkwi2NXxaOV9VbN45mV1nmCYCFBr5d0s6jyK42slgI1n27idJJGZU5er81UHDNhucIGGvM91cyXHx880GmtQxTyqIPQk5xUlTlsutvQ99MPq4TbBAwATcyGJo9b82CGEQkEbi1oFZSPu8eSQlFPeQPxs"));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                Roles roles = createRoles(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = roles.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(roles, null);
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 2:
                        roles.setRoleName(contraints.getNegativeValue().toString());
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(roles, null);
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 4:
                        roles.setRoleDescription(contraints.getNegativeValue().toString());
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 5:
                        roles.setRoleIcon(contraints.getNegativeValue().toString());
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 6:
                        roles.setRoleHelp(contraints.getNegativeValue().toString());
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (failureCount > 0) {
            org.junit.Assert.fail();
        }
    }
}
