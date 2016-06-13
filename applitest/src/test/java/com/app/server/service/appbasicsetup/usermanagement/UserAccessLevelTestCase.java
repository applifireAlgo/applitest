package com.app.server.service.appbasicsetup.usermanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.appbasicsetup.usermanagement.UserAccessLevelRepository;
import com.app.shared.appbasicsetup.usermanagement.UserAccessLevel;
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
public class UserAccessLevelTestCase extends EntityTestCriteria {

    @Autowired
    private UserAccessLevelRepository<UserAccessLevel> useraccesslevelRepository;

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

    private UserAccessLevel createUserAccessLevel(Boolean isSave) throws Exception {
        UserAccessLevel useraccesslevel = new UserAccessLevel();
        useraccesslevel.setUserAccessLevel(valueGenerator.getRandomInteger(99999, 0));
        useraccesslevel.setLevelDescription("GhUkIf5qx2E7wISn9K1DYBoMSXep5e16lQ7a8WpzhZsC4xydvA");
        useraccesslevel.setLevelIcon("d1Wb8QSe27QEthau0ZEtdocfqvHVgBNBF9tify0Uy8GnZ748oo");
        useraccesslevel.setLevelName("4klCFTem19xz3eLgUPUwCVcWx5oRcHY5bCCa3esWmxltwiZ7HG");
        useraccesslevel.setLevelHelp("9MLXInGLNaMxNvMP5OCFCs5LhxPIF0FgFTolz6hBcbhFNKnXnG");
        useraccesslevel.setEntityValidator(entityValidator);
        return useraccesslevel;
    }

    @Test
    public void test1Save() {
        try {
            UserAccessLevel useraccesslevel = createUserAccessLevel(true);
            useraccesslevel.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            useraccesslevel.isValid();
            useraccesslevelRepository.save(useraccesslevel);
            map.put("UserAccessLevelPrimaryKey", useraccesslevel._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessLevelPrimaryKey"));
            UserAccessLevel useraccesslevel = useraccesslevelRepository.findById((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
            useraccesslevel.setUserAccessLevel(42283);
            useraccesslevel.setLevelDescription("vBwFirOVODzRK7YOnpYWAyJmwddwA9z1brT0boX9a6er8dcRvC");
            useraccesslevel.setVersionId(1);
            useraccesslevel.setLevelIcon("zAoEPJfclQP9JSGq9trwxeCiMG9SFdjlHTlSCrHIv7SuAll7WG");
            useraccesslevel.setLevelName("O6bYU7v0Xex6udScFsbBTaWhmoj4bgwxi7R4ttS3BtboG083qA");
            useraccesslevel.setLevelHelp("RKWP2O3HXQ2kl8L7IP6dFdYd51rOKycQGWbegxRB5mFBBAUgeV");
            useraccesslevel.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            useraccesslevelRepository.update(useraccesslevel);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessLevelPrimaryKey"));
            useraccesslevelRepository.findById((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessLevelPrimaryKey"));
            useraccesslevelRepository.delete((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateUserAccessLevel(EntityTestCriteria contraints, UserAccessLevel useraccesslevel) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            useraccesslevel.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            useraccesslevel.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            useraccesslevel.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            useraccesslevelRepository.save(useraccesslevel);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "userAccessLevel", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "userAccessLevel", 147282));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "levelName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "levelName", "SHiWqsyraiq5oO1bCuPUfHzaZenvB49JYXpWxiH8lJTOH8uID6PovfERmpXNiRT70achfT8umyM7PagwFnwhUwh6XTPle1jnSe5zQJs5xW3Sqmv5nW5hwKPrnWtYxMrzlY2EpdhX3PI2iyVVCkUnfhL4TBhgqU5zOhUcI5lTpFufhI9F0ktgTHLyygM3hDHx6e9ucE2Z0DPzUOVqeF82WdmyyKWHiI9WgEDknaagkkZV5etaptRvZzbwFENBZgKj6"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "levelDescription", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "levelDescription", "KDgPmMn8LiOeIjI1dSiV4x7U5L6ypafa1SVfmcuHk0i6Bhttjbz07nrOyq4wgm6BUCSjDv3fyVR7JOBakzigWzEYszlBHpsLSc7n2YHhzAl83245GmkLXgOjOqYQhWXs6ciZvgfk7hVfWOfaU6QViiW5732BUMQuZ47IKqNV3AWaudVSbKQJVttIIWf4lH6G63rE58scddCVWyEoy1sb6zW99DFQgGKlmpFhgxxfNUHencFE8BhMBOcqB14LRsL8b"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "levelHelp", "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"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "levelIcon", "F9XyrgS57CyOaC95gEjeHIRdfBBYjBpt5m2eUznmnlmLjpfNRFqDmziP1pONoLZKOFOw2aAkabj3ByP2YLZFzFL0opJpcZf7gIcaklJR6PprrsBQI4M9rJfvmPpfgXeZBKIP6ZN7tU8mAufaB6Ui42dEKsSErytu3uTIB4c0wZqEmMM3nPDYp2o0BSl1KBmnQekCHgEdCiJwmvddxws8hwxilH0pz2vIOWtSjMCmPNGwnSTwBKpwynjwOjb2KmNeB"));
        entityContraints.add(new EntityTestCriteria(UNIQUE, 9, "CombineUniqueKey", ""));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        UserAccessLevel useraccesslevelUnique = useraccesslevelRepository.findById((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                UserAccessLevel useraccesslevel = createUserAccessLevel(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = useraccesslevel.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(useraccesslevel, null);
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 2:
                        useraccesslevel.setUserAccessLevel(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(useraccesslevel, null);
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 4:
                        useraccesslevel.setLevelName(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(useraccesslevel, null);
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 6:
                        useraccesslevel.setLevelDescription(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 7:
                        useraccesslevel.setLevelHelp(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 8:
                        useraccesslevel.setLevelIcon(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 9:
                        useraccesslevel.setUserAccessLevel(useraccesslevelUnique.getUserAccessLevel());
                        validateUserAccessLevel(contraints, useraccesslevel);
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
