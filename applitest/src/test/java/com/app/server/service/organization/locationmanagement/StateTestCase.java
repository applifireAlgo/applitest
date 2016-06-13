package com.app.server.service.organization.locationmanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.organization.locationmanagement.StateRepository;
import com.app.shared.organization.locationmanagement.State;
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
import com.app.shared.organization.locationmanagement.Country;
import com.app.server.repository.organization.locationmanagement.CountryRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class StateTestCase extends EntityTestCriteria {

    @Autowired
    private StateRepository<State> stateRepository;

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

    private State createState(Boolean isSave) throws Exception {
        Country country = new Country();
        country.setCurrencyName("O3gMx78bdxR4x7TEzxYVew3ZxBqnJbuIwtwnqY3xH3g19pIrRf");
        country.setCapitalLatitude(8);
        country.setCountryName("4fDhciH4FBGpQlTquIzyi9TK7ggtugWJ7AMklLkGLLJoN99mrl");
        country.setIsoNumeric(681);
        country.setCurrencySymbol("SUoiMQiAOgE1t1Ih7yOQy9od6PmHhFQN");
        country.setCurrencyCode("PGO");
        country.setCapital("S2BbadJcndv6gACCj6jMRnmcNLczW7bg");
        country.setCountryCode2("YFj");
        country.setCountryFlag("eqvwpL4UvNSoYYgDolm6nCQl8cMEtDCg7sCT3R4G3cTdApxZuz");
        country.setCapitalLongitude(6);
        country.setCountryCode1("T4J");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        State state = new State();
        state.setStateFlag("EqDQGGXbK36vOwAPimI9FIRiv22X0XeiNdhjVCuErfDBpJTh5i");
        state.setStateCapital("amTjZsqeV0oKQKnAZDqJ76xIl8uQItaO3VvY7OnWMM8bRQvNdv");
        state.setStateDescription("7Y8nt65EoQ2zuIYHccuEojZKs40aa8aez2wTzIFtiuz9h0tcyH");
        state.setStateCode(1);
        state.setStateCodeChar3("gmsfAEry02q8w180VD7fbRirXWLmYlP7");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey());
        state.setStateName("VUqlQteFAexaX1lO2Ox9HgvW6WA0IAa5ewRbOuShCCoo7Hs57U");
        state.setStateCodeChar2("XnXd4DdYhaFoGkSTkLMmi6HQHsbyrPSi");
        state.setStateCapitalLongitude(8);
        state.setStateCapitalLatitude(1);
        state.setEntityValidator(entityValidator);
        return state;
    }

    @Test
    public void test1Save() {
        try {
            State state = createState(true);
            state.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            state.isValid();
            stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("StatePrimaryKey"));
            State state = stateRepository.findById((java.lang.String) map.get("StatePrimaryKey"));
            state.setStateFlag("2I375uWCzksfbYIeBzupV0Xv0BNRx1GddtdwpDLpBKDKZyHa7v");
            state.setStateCapital("tFPqNjJkzXwL0rekvuEFyIl48Ll4lgyIzI0sudtUoUnLq3NflP");
            state.setStateDescription("47T2sSV9UP2JMwMKWQnzUhKT9rPI2cL5ntOCkc3k0u8jdl5Hbp");
            state.setStateCode(2);
            state.setStateCodeChar3("NYKW32R5Erlu0AxvZdBsAfYj21Zcoxsm");
            state.setStateName("8ffZ7Fg5E7U3ywWAUy5IZj8qkxTcTshQRBfcQa3QJxoLrpx5WT");
            state.setStateCodeChar2("Ftykcq6C9vhcDdmTxQPRrEYHxQmdAiLn");
            state.setStateCapitalLongitude(8);
            state.setVersionId(1);
            state.setStateCapitalLatitude(10);
            state.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            stateRepository.update(state);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycountryId() {
        try {
            java.util.List<State> listofcountryId = stateRepository.findByCountryId((java.lang.String) map.get("CountryPrimaryKey"));
            if (listofcountryId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("StatePrimaryKey"));
            stateRepository.findById((java.lang.String) map.get("StatePrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("StatePrimaryKey"));
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateState(EntityTestCriteria contraints, State state) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            state.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            state.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            state.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            stateRepository.save(state);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "stateName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "stateName", "BtPcQKBMb1k3vJ6QBjngcCsDT6NM8yH09gprIIQ4VVyxlfiv74PjJfz7LJb8dIpF3nAhtncMHGgS4WIH17EOn98JeT4vBcKEhXacVlLx2Ae0clWxbebhSkswWxcESWeC8WZePZMXKD4oXiiY20V2xzhJqsAL6psC18tgQqYLMGBKbfyrWO1bGToDCnXzNCHp2pJhHefeF351QBe8BWz4c3Y0eDGKmP6Uk2WVn9c9omYnRpEWLwjU5tJbPbgHEXks6"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "stateCode", 4));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 4, "stateCodeChar2", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "stateCodeChar2", "Uab8qkvCAkBUZGz21wRrtY8pLCMJjfKSb"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "stateCodeChar3", "hy3kUrD5B9sRyq6TiU56sYvCHk3zk3RNL"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "stateDescription", "6lT2eORbNyCy5QRlrVRszKovmlTPO4nweDSTTnccHRtMRmPmPcM8y2RKsT01w1IjYeknzdapjqaExgDkgq51NvyxoGhNeom2gmvTIWBjJ5EKY0KqZrSTGkDaKLI3uJlNqGruuP0T59ALug5upENpYb9yE6AATGH9ihTK0XtvLR8CNt2ZyzGYSwrTfeBvPqTYY0CnWiclVo2tJVRBqbYqhpBN2RfNxbOJI4cXKqK99zrXK8r5Z8iPm2xn5ZOQRBE6K"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "stateFlag", "Bm0gb97HYWw5AordJmzXNFfRu8PkOjDMWV4RPfIMgnBrZPxi7smIf0FZydTMBClUIlPhhS224NrfeUAyGBG24lX7gNMjAa8wIgl2JQfUnpVKdFYK3QP2uiRQuAlwWGs4f"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "stateCapital", "f8NBPIGOdJx9dBVFS9jFnakhYTEkb3EOJQRHI7rpJAMPCTfH3o1PK5aYugS8i3o2KlRmhTvhWJmcV7zWdZphSweCWTTOXNxtNlyS2w8ADozhTYpiXQtzILpQBJOTGSfkU"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 10, "stateCapitalLatitude", 14));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 11, "stateCapitalLongitude", 12));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                State state = createState(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = state.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(state, null);
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 2:
                        state.setStateName(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 3:
                        state.setStateCode(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 4:
                        field.setAccessible(true);
                        field.set(state, null);
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 5:
                        state.setStateCodeChar2(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 6:
                        state.setStateCodeChar3(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 7:
                        state.setStateDescription(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 8:
                        state.setStateFlag(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 9:
                        state.setStateCapital(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 10:
                        state.setStateCapitalLatitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 11:
                        state.setStateCapitalLongitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateState(contraints, state);
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
