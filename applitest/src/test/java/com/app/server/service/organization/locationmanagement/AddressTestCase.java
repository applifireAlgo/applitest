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
import com.app.server.repository.organization.locationmanagement.AddressRepository;
import com.app.shared.organization.locationmanagement.Address;
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
import com.app.shared.organization.locationmanagement.City;
import com.app.server.repository.organization.locationmanagement.CityRepository;
import com.app.shared.organization.locationmanagement.Country;
import com.app.server.repository.organization.locationmanagement.CountryRepository;
import com.app.shared.organization.locationmanagement.State;
import com.app.server.repository.organization.locationmanagement.StateRepository;
import com.app.shared.organization.locationmanagement.AddressType;
import com.app.server.repository.organization.locationmanagement.AddressTypeRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class AddressTestCase extends EntityTestCriteria {

    @Autowired
    private AddressRepository<Address> addressRepository;

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

    private Address createAddress(Boolean isSave) throws Exception {
        City city = new City();
        city.setCityCodeChar2("NnSuDs4vvFpxMpbtezXCmNAPEQgNw1pt");
        city.setCityLatitude(11);
        city.setCityName("p3kxPxRbhHkb9QlYdiR7R2BZq9rZT7O7tyFGEpQQR5Lmrxuklh");
        city.setCityDescription("A0EoJ1yATL9pmCdLAlYgZfkuLKqOouVg37RrO0TY87qt7j0zQw");
        city.setCityCode(3);
        Country country = new Country();
        country.setCurrencyName("ObWEMrNragUqY7ziqmGewSRmev1APn8lEmZgwjbAYhCJKRuBaO");
        country.setCapitalLatitude(7);
        country.setCountryName("6lUP6zed6ZiitrsdpYASGTphK5VKEw8wf42aEt9G9dg8d7f33l");
        country.setIsoNumeric(501);
        country.setCurrencySymbol("FAJ67JLRFeso8mbpMridaxkkDE8n2ttJ");
        country.setCurrencyCode("xhA");
        country.setCapital("QMRwXhRck0FdyYTGxpZCvCkDm5Fs4im5");
        country.setCountryCode2("7Bi");
        country.setCountryFlag("u93emPoUHPmqkMg6scWAlQGM2An5QBBX65P6dnljVybACQEvVl");
        country.setCapitalLongitude(8);
        country.setCountryCode1("1Gk");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        State state = new State();
        state.setStateFlag("I3TRV2OiZGsBR7fdN27635qaQ9avu9bMDJmu7a6MPzKaCn1h7e");
        state.setStateCapital("wgT4aQ8dvUkp5jFwKQFzzTtxB2hcaLOcXCM9vcEt5aLRmwwbMq");
        state.setStateDescription("ia1EjOh7EOfH0t3o8DGV6IEPwTz7KFwIMPvxI4OpRXfJlEyLIn");
        state.setStateCode(2);
        state.setStateCodeChar3("L6SQwRO9JDBF1U7LrZSOkLk7KzpINglP");
        state.setStateFlag("dz8d49jwvCtbTqgIASlxKa24R3wrZdXWWZAC4ejIATaTilLt1I");
        state.setStateCapital("2uKA5gFZbiDiaoX2Qiq2mAfxECEeqiGtA589cIhU9NsV8z4Y0C");
        state.setStateDescription("QekytS3HcDNGCuKdiGzMCIwj4xtd9nKL2CKw2PFHcILbSxNW8j");
        state.setStateCode(1);
        state.setStateCodeChar3("cZxmFI0D2zxLGWvoj3b7n1VCGN7OrbJe");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateName("wTMzSNYBwLsEWspdxVtGTlrXssOR4YTGy7ePqIoxjesfA9kn4d");
        state.setStateCodeChar2("U29BGJuCt1d8cIp7UFkvAgOkA9spuXSK");
        state.setStateCapitalLongitude(9);
        state.setStateCapitalLatitude(6);
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        city.setCityCodeChar2("KoVdW792zUG916E5ArMoHAV2E3oq8u4Y");
        city.setCityLatitude(10);
        city.setCityName("TRU5OzOBoxGE3gCpPqo2d6kandrGPr5F1pRXyy4v1DZBGgsrQn");
        city.setCityDescription("Ppe0TP8v3mjB068ZyOi9YH55Zphp3OfIpuIn7VpsOXc2D5RlzK");
        city.setCityCode(2);
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityFlag("rL1WRlst0zP6rfBTlkUeLK7cCRxnTLQu8GC3qMbOg681W7elQl");
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityLongitude(1);
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        AddressType addresstype = new AddressType();
        addresstype.setAddressType("YMPkns8DfWwbzQpryoV5Uyee7udh1qt9yWswE3YlNASpzvw8cZ");
        addresstype.setAddressTypeIcon("lf6TlDktl81kHQAB2H9jkPBPBOMAvHdwQ92BYaky1LB6jg6hN1");
        addresstype.setAddressTypeDesc("fI5PtTfjpTBF9xBQyHmknRpVbLtnIKVW3llM7Voj5w0UT34DAa");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        Address address = new Address();
        address.setAddress3("mhL3mh0RGEyV6usBxDtFi8aOj2cdyySzfQI9l3n1UDxzO1Lc5F");
        address.setLatitude("OxoxNBwiLDjcY53LmsUVcaylOuqb7GQqG6L9UJ4Il1hifX8Sdi");
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setZipcode("paA8Rv");
        address.setLongitude("6fjtHgcSOp9h4Rlh6hvvepfup0Df9Nm4JFSm1jX601GsVrhcmd");
        address.setAddress2("pTZGdkC5P1kdbkL2SMkoIpiPTH0AGvo5OfFRrpjpa6kcCzAA7V");
        address.setAddressLabel("yUtJ9tfpmTG");
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress1("EVxBb6o2MIZVXPgxIoVfZB6WyKzSCrfmsj9NHLaYFOEc5SjhTb");
        address.setStateId((java.lang.String) StateTest._getPrimarykey());
        address.setEntityValidator(entityValidator);
        return address;
    }

    @Test
    public void test1Save() {
        try {
            Address address = createAddress(true);
            address.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            address.isValid();
            addressRepository.save(address);
            map.put("AddressPrimaryKey", address._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private CityRepository<City> cityRepository;

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Autowired
    private StateRepository<State> stateRepository;

    @Autowired
    private AddressTypeRepository<AddressType> addresstypeRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("AddressPrimaryKey"));
            Address address = addressRepository.findById((java.lang.String) map.get("AddressPrimaryKey"));
            address.setAddress3("dLfeRu8LdACjd6B6bo3rGQRD5xOBRU7du8YySnzBmI43zHluHw");
            address.setLatitude("abXkouCBBWierS8knkJIaTmAxUc3zZhb2qDVzoLWGopBeFBn2r");
            address.setZipcode("4lc5fc");
            address.setLongitude("Ot8R5EhJuQQ7Hd7qLHb6WaCciodJlTXAz8EG2z59fAdJ1TxYSW");
            address.setAddress2("ePvp3XnsSHfRWOE2EuF4sNvJ9mJ2f4RU8kQu2oSdMBr0w7T5vU");
            address.setAddressLabel("Zx6pZUM0icj");
            address.setVersionId(1);
            address.setAddress1("M0ucLqxFc1kEMRc0kgYuNVG7YcOpioKTqBONt0TuXUMSdFOIfx");
            address.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            addressRepository.update(address);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycityId() {
        try {
            java.util.List<Address> listofcityId = addressRepository.findByCityId((java.lang.String) map.get("CityPrimaryKey"));
            if (listofcityId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("AddressPrimaryKey"));
            addressRepository.findById((java.lang.String) map.get("AddressPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycountryId() {
        try {
            java.util.List<Address> listofcountryId = addressRepository.findByCountryId((java.lang.String) map.get("CountryPrimaryKey"));
            if (listofcountryId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findByaddressTypeId() {
        try {
            java.util.List<Address> listofaddressTypeId = addressRepository.findByAddressTypeId((java.lang.String) map.get("AddressTypePrimaryKey"));
            if (listofaddressTypeId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBystateId() {
        try {
            java.util.List<Address> listofstateId = addressRepository.findByStateId((java.lang.String) map.get("StatePrimaryKey"));
            if (listofstateId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("AddressPrimaryKey"));
            addressRepository.delete((java.lang.String) map.get("AddressPrimaryKey")); /* Deleting refrenced data */
            addresstypeRepository.delete((java.lang.String) map.get("AddressTypePrimaryKey")); /* Deleting refrenced data */
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateAddress(EntityTestCriteria contraints, Address address) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            address.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            address.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            address.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            addressRepository.save(address);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 1, "addressLabel", "NZviWmak5XhW"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "address1", "4oV6roha8HbVoSCwxESQOShJnrxf4YABWhKz7Joh2vWul8dqALN4COu1F"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "address2", "Ic5uYTbSWyxkK6rvEVpv0RUK1ckZuJlS4rkocHoTonfSeeNXE66vaAcLS"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "address3", "uSOMDCgSOqJDRfDB2xbCD14y1LuJkaYQkbBZ4by6GPLRvCNVI20oRQs6p"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "zipcode", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "zipcode", "SFfbfNJ"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "latitude", "rIDoEx0cxUR5WtYFF5zYdjwgEhz9djSTr9vh89BbnqxJnlP7p89DEj4fsJQ2rOtdU"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "longitude", "hPCdNuiOnZ5nAdkMQh4aPKMKKT4VxnxEUmY0iYl3K13oTkyM7stg6ZbYN2KHnCPqe"));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                Address address = createAddress(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = address.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        address.setAddressLabel(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 2:
                        address.setAddress1(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 3:
                        address.setAddress2(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 4:
                        address.setAddress3(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(address, null);
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 6:
                        address.setZipcode(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 7:
                        address.setLatitude(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 8:
                        address.setLongitude(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
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
