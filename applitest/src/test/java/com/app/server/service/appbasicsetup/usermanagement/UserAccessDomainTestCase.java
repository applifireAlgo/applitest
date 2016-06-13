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
import com.app.server.repository.appbasicsetup.usermanagement.UserAccessDomainRepository;
import com.app.shared.appbasicsetup.usermanagement.UserAccessDomain;
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
public class UserAccessDomainTestCase extends EntityTestCriteria {

    @Autowired
    private UserAccessDomainRepository<UserAccessDomain> useraccessdomainRepository;

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

    private UserAccessDomain createUserAccessDomain(Boolean isSave) throws Exception {
        UserAccessDomain useraccessdomain = new UserAccessDomain();
        useraccessdomain.setDomainDescription("pXfmMsKMp6RiaZhEeVXkrM6ZHwoS0cK7fXRlWK8NkVt9ByV0tD");
        useraccessdomain.setDomainName("egPno75CFOqwQ0KLsu8Wne0Mjxu9q3SWAJ7CVPEzWvtxIxlmNx");
        useraccessdomain.setDomainIcon("ZjZ4EBY4C2YrbPqb6oxUWHgzH4WsTjDClfOb2CGIi2nksenJio");
        useraccessdomain.setUserAccessDomain(valueGenerator.getRandomInteger(99999, 0));
        useraccessdomain.setDomainHelp("WSuAshismn0ANmUDMtQDGkQZohWCz9i3UGfhBcMAB1Bm0qHJbS");
        useraccessdomain.setEntityValidator(entityValidator);
        return useraccessdomain;
    }

    @Test
    public void test1Save() {
        try {
            UserAccessDomain useraccessdomain = createUserAccessDomain(true);
            useraccessdomain.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            useraccessdomain.isValid();
            useraccessdomainRepository.save(useraccessdomain);
            map.put("UserAccessDomainPrimaryKey", useraccessdomain._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessDomainPrimaryKey"));
            UserAccessDomain useraccessdomain = useraccessdomainRepository.findById((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
            useraccessdomain.setDomainDescription("Pm5B9Wf0KknoDOekWJ4ALVZa3qpWmk0dEAgQKyMX9IMAYgKYHO");
            useraccessdomain.setDomainName("dAdhejeaEc1pFnuHqDhqBdn9QQscUd6JBJ84MzchI7gtrw6uEk");
            useraccessdomain.setDomainIcon("bhATghtLh2gCJ77TgdzzRjWUZBLPVjgFBazVWYYR53wJJShC50");
            useraccessdomain.setUserAccessDomain(83865);
            useraccessdomain.setDomainHelp("UwT6ClOyWGYAHArqn3AglJtxDEVfFNs6f3vI7JxTwtVkIp8Emg");
            useraccessdomain.setVersionId(1);
            useraccessdomain.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            useraccessdomainRepository.update(useraccessdomain);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessDomainPrimaryKey"));
            useraccessdomainRepository.findById((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessDomainPrimaryKey"));
            useraccessdomainRepository.delete((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateUserAccessDomain(EntityTestCriteria contraints, UserAccessDomain useraccessdomain) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            useraccessdomain.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            useraccessdomain.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            useraccessdomain.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            useraccessdomainRepository.save(useraccessdomain);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "userAccessDomain", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "userAccessDomain", 178490));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "domainName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "domainName", "VmRtefB4KwO9UgGKKnNbCpPpyn6NTjMVygjtTnPRcOWklwJR2vVVmjX6qu84Os9oj178XggD1PQuyJE1EOBjBdYtUgWDomjDGnCxkzBJXE5sPtt176lWFtEPcvzJgPB6JIldphNqC1eBJtvJEXgff4jODjEgdmBkq9zydRsyxih4FeqVDzayi0gW4WmVwZtuwANiyoDHdhLN0ju9suGgekdhgACGlFtkYoyCUxKl4E0WyRgsN2SWunyEXzfZOmJIi"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "domainDescription", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "domainDescription", "TdvARzROTscmrj7h9IgZjldf1DWy1BOIrKA01DcXcxcnixWSFtWUPqfqs9BAcE96mIZ40JcdJwxV6VL61B9JVwnz0yqFNasqda2kVtAvCXzz61B0bDUTS5oyinU8B1DDaSEi2qSP2osGyXqGybZV5d5rL1c3vKIlIfce82e7bkPZdcUOTKFFHIzjJobu8qkECLyqRu9uQ08iDGxhsxt4R8aMFPNCFHkNe6Qmi0N8DqYDJCTNisjbIqw08Pk3LqyGR"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "domainHelp", "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"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "domainIcon", "jeVe83volTUrkaZZVHCUogEa8zIDKKILWUveOwiZTN3OPlrBtMfTXsQFc0aQYK7eQKZOEhCtKPDOyMReuhw6JWnB9i15HnUariIxjOPzQGfzED5v6loYgZd5aBG0yJdNTWT8WQd6M4isf8njJctQ2Nvb8pr2RxwSTqRDV9a6INzdnETvnJFeo8rNL5B3ErBSsVJx0NOc5PPcPQRTZ1Z7ds7JF4qpUXCfvPo0e6LpSAdRJBqG5OfdHaLH2XjHBiS9U"));
        entityContraints.add(new EntityTestCriteria(UNIQUE, 9, "CombineUniqueKey", ""));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        UserAccessDomain useraccessdomainUnique = useraccessdomainRepository.findById((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                UserAccessDomain useraccessdomain = createUserAccessDomain(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = useraccessdomain.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(useraccessdomain, null);
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 2:
                        useraccessdomain.setUserAccessDomain(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(useraccessdomain, null);
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 4:
                        useraccessdomain.setDomainName(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(useraccessdomain, null);
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 6:
                        useraccessdomain.setDomainDescription(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 7:
                        useraccessdomain.setDomainHelp(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 8:
                        useraccessdomain.setDomainIcon(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 9:
                        useraccessdomain.setUserAccessDomain(useraccessdomainUnique.getUserAccessDomain());
                        validateUserAccessDomain(contraints, useraccessdomain);
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
