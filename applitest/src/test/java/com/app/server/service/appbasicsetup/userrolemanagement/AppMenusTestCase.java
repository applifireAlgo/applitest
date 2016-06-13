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
import com.app.server.repository.appbasicsetup.userrolemanagement.AppMenusRepository;
import com.app.shared.appbasicsetup.userrolemanagement.AppMenus;
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

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class AppMenusTestCase extends EntityTestCriteria {

    @Autowired
    private AppMenusRepository<AppMenus> appmenusRepository;

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

    private AppMenus createAppMenus(Boolean isSave) throws Exception {
        AppMenus appmenus = new AppMenus();
        appmenus.setUiType("ol3");
        appmenus.setRefObjectId("te3woxX05cwc21GwpqCgAxMzZusYR7kzRmY0rCuWulAVb5l1uL");
        appmenus.setMenuDisplay(true);
        appmenus.setMenuLabel("uoSYdmIMLDfQSdP1CBQZOcX4L7qMcONA0oUVINyzFYSlRrMuaH");
        appmenus.setAppId("9NuOuUKn8gsZSOTjrF9HkJKRnQfPX1AYtqlEya0wvdiIIwbCWP");
        appmenus.setAppType(2);
        appmenus.setAutoSave(true);
        appmenus.setMenuAction("L8x3Np8yyxCtXF9ZLWTCASI2O9wGy8lUIYlaX3vpXnUe0SLAaA");
        appmenus.setMenuCommands("zf002GsA7o9gVRIxf8tYwfjtNRqUUTcOR9Fo3bUhQrTNwpcpMR");
        appmenus.setMenuAccessRights(4);
        appmenus.setMenuIcon("bBrZxKtVFnapmGDhCqZNuF9qEYMGWGnGaOUkNxwOa0byBMMTPn");
        appmenus.setMenuHead(true);
        appmenus.setMenuTreeId("1LfWbLDmnrYQFUVxLI5o2WUpC7UYcXrR3v6drjXgQ0lrlnIDrB");
        appmenus.setEntityValidator(entityValidator);
        return appmenus;
    }

    @Test
    public void test1Save() {
        try {
            AppMenus appmenus = createAppMenus(true);
            appmenus.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            appmenus.isValid();
            appmenusRepository.save(appmenus);
            map.put("AppMenusPrimaryKey", appmenus._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("AppMenusPrimaryKey"));
            AppMenus appmenus = appmenusRepository.findById((java.lang.String) map.get("AppMenusPrimaryKey"));
            appmenus.setUiType("FMc");
            appmenus.setRefObjectId("uR3jPHOS19mXCAMSnrezrZPX88B0WElYJs9Emj8zWFLBSHe9hu");
            appmenus.setMenuLabel("FwLyvopwQKBsmzbyIl6V4lKsf7v75XnCHVxqod88se7FvKP1rc");
            appmenus.setAppId("jLRfXplpEWe8OPNWpSCvx1CAle1y3CDnIrtY3gUGCNT3nkdQfw");
            appmenus.setAppType(2);
            appmenus.setVersionId(1);
            appmenus.setMenuAction("yfXgSynlCawEBOwpPnbYJKDjr54sUlCIQPN2z8d86clJiWxjoz");
            appmenus.setMenuCommands("SErPxHEcI0qhEvxierhC3vjFemBjZuQLRLhKflYeCVHWD1751X");
            appmenus.setMenuAccessRights(7);
            appmenus.setMenuIcon("bUw7RCxY2DKou4Gv4IxlNwYhMeaSjja6cGMRKavfnUT4vkVwoP");
            appmenus.setMenuTreeId("rfZ8y1azYRVj431znKII5opjFKdghIAAcfgvJTsjbYpMryftZY");
            appmenus.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            appmenusRepository.update(appmenus);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("AppMenusPrimaryKey"));
            appmenusRepository.findById((java.lang.String) map.get("AppMenusPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("AppMenusPrimaryKey"));
            appmenusRepository.delete((java.lang.String) map.get("AppMenusPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateAppMenus(EntityTestCriteria contraints, AppMenus appmenus) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            appmenus.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            appmenus.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            appmenus.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            appmenusRepository.save(appmenus);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "menuTreeId", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "menuTreeId", "1CLK4qqIkr7quLr0uOWaxJHthUmZ1ieLhPAkcrDNqBmkJs6hc73X6vEF1GE3ESQhwyBwbM4JTbuoJPvsehuW2HzaBtP5dB5HDikrQx9oTxtPdZajD26n9uFclboCRWe2SaLmT3xo4jYA60ugFsimVVoJZrXTznsXK2lJYds3EEhGv6uVcESNhw500hO426sQX3jn61H6lxZg7XqTprp24SIEW7TfYdD5u6vKo2aYgU1kvTRWQ5ar3RzkO0ItekAcN"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "menuIcon", "iIVZx9xFwlnMX3SwHDwmCd6uh3pCCDHv4HZu0ltTcMFjXy8cUNLiTEmUMxGHHfjHEyB2KFc2JMbPvJiUPIKh39mPvj7Dpi7VHty0mzqtoE0R1KuY1YXxtsbPYHaxWSsf7XGhKrGAy1aOnGDwVP8ZZ5i1IdWkqKK3CvokmU8fDDS5lfGK3Trr5bogotStIe7dAfrdYCW0SOPlB70a0QM7yZrK8HuDOZ5vpxtFeahvLnSmGVFGddFSvjPH0lNTqsZPY"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "menuAction", "Mq0dKHR0htt6eoURr33WlwJwAQQQXizxR1PhUFQJs78Fevox0sBkgcynBlvGs3Fnngo6rrQOtLI4NjFFw2vIUoAIo9RiZmkDYtuOElLdEdttiSQqJIdub2zzlFvPLZhGuCSoFMMFS4gh3855FNrLq2q26n6LHQSqAydAEjdxGNwqTo9VWeOP4nkWv2gq9EkirwhD678ZMIwKI8Y7DRWnFu1ZcxSWxvn4ErAedDPRFYPruksmtJLgN108Ug4FjfZAB"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "menuCommands", "BB03gMi9wEWYs0kK7aThTKOVQrEmVRYAlvo9F4ySa0T6nKEun7spr9nQ49rfBN9gy"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 6, "menuDisplay", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "menuDisplay", true));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 8, "menuHead", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "menuHead", true));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 10, "menuLabel", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 11, "menuLabel", "fQSqd8VFo5TPZjFCDaB4s2YojiwJABlH0h6hqxrEt2gi7tfdb7HErDDjtPdfWw8ew1h3Crt2eu3ZM6A9uvRtoAAMMcNs358LtAmDV9rgMsK4y5ZuAGCJsF3A8zRNvFXMiUZvsvNkTgfMpqk0du3uTqD5ytDVuo4Zf0yGUtO2Eqj7LKweeiRv4KVlrYd8Y8b356yzLI7D76lKaOuayET3RBS88cFG8vVfONctBTDqx4TuBkMqsizQsIPZcJuvS6FVl"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 12, "uiType", "oq6G"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 13, "refObjectId", "kOG6EBSGnByvchY3GqKL7XofiLOm48CImS9hwZmyjtLRSIbm81fHf8OlRZo6WFN8EewF3eyZe28apudG5W9pXl0ABSMzmRMlBUcrHnn7bgGP5H9HVKf19Po7z3tvHt97SDZtMQjzll1BySVEzVijP9CgN0Kpphwps2uVtrgVknalGKxlqiLYlYKCnwcmKnaEXbeAxDh6lHWXxDI0KGEScFA6jxr5hwF8yFQbM509xQtqZl42apeWWhPoWndfm9liA"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 14, "menuAccessRights", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 15, "menuAccessRights", 21));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 16, "appType", 4));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 17, "appId", "uCNpRfyvXEUHqgbEvUAZSCS6qxEUI4MYVEpo7WNyyaljPisZn0PN9SgYf0WyVDbrXHzxiBsdhus5jQwdKslcEFyPl7Gt9jzBE3PTAnBG10fNOOpkMlHzZj8othNsEJrR2mU6T10YEUQPqGeogJwkMkaFsYo7p7LMi0Kx81fAnGqqQdEGnoFNMdSZmbbtEDFrRYwtwP6RdJEi88ToDmTSIBkAYjZOBPppjxaxRBr8Wp1PwdhbYhlDI32YEYKchqtIZ"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 18, "autoSave", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 19, "autoSave", true));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                AppMenus appmenus = createAppMenus(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = appmenus.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 2:
                        appmenus.setMenuTreeId(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 3:
                        appmenus.setMenuIcon(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 4:
                        appmenus.setMenuAction(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 5:
                        appmenus.setMenuCommands(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 6:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 7:
                        break;
                    case 8:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 9:
                        break;
                    case 10:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 11:
                        appmenus.setMenuLabel(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 12:
                        appmenus.setUiType(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 13:
                        appmenus.setRefObjectId(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 14:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 15:
                        appmenus.setMenuAccessRights(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 16:
                        appmenus.setAppType(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 17:
                        appmenus.setAppId(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 18:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 19:
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
