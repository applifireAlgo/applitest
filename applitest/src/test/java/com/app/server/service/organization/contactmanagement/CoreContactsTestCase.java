package com.app.server.service.organization.contactmanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.organization.contactmanagement.CoreContactsRepository;
import com.app.shared.organization.contactmanagement.CoreContacts;
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
import com.app.shared.organization.locationmanagement.Timezone;
import com.app.server.repository.organization.locationmanagement.TimezoneRepository;
import com.app.shared.organization.contactmanagement.Gender;
import com.app.server.repository.organization.contactmanagement.GenderRepository;
import com.app.shared.organization.contactmanagement.Title;
import com.app.server.repository.organization.contactmanagement.TitleRepository;
import com.app.shared.organization.locationmanagement.Language;
import com.app.server.repository.organization.locationmanagement.LanguageRepository;
import com.app.shared.organization.contactmanagement.CommunicationData;
import com.app.shared.organization.contactmanagement.CommunicationGroup;
import com.app.server.repository.organization.contactmanagement.CommunicationGroupRepository;
import com.app.shared.organization.contactmanagement.CommunicationType;
import com.app.server.repository.organization.contactmanagement.CommunicationTypeRepository;
import com.app.shared.organization.locationmanagement.Address;
import com.app.server.repository.organization.locationmanagement.AddressRepository;
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
public class CoreContactsTestCase extends EntityTestCriteria {

    @Autowired
    private CoreContactsRepository<CoreContacts> corecontactsRepository;

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

    private CoreContacts createCoreContacts(Boolean isSave) throws Exception {
        Timezone timezone = new Timezone();
        timezone.setTimeZoneLabel("zMBJRUNPi9FfuBvGU8wm7lTtLgFNdzq2QxS9eYGp2XCEXkqcnO");
        timezone.setUtcdifference(11);
        timezone.setCountry("tEv9X46vO2TSmIYPlWAPcpmZdQ9EBhEolMvhCFaTtjz9nOHLTb");
        timezone.setCities("QOZKkTRYNi9iBpcwsakoBIO4mfdD5nyufplCAe8px08t0iOV89");
        timezone.setGmtLabel("B0HZ8ZZDJaXiydHDSXYEjXjZDXupnczZx6O0himprjFJsHNqUx");
        Gender gender = new Gender();
        gender.setGender("ZAGmRKe7zfoCImmrTtlIa6lLz30pbFuIvas11rtmeasSGOi5TO");
        Gender GenderTest = new Gender();
        if (isSave) {
            GenderTest = genderRepository.save(gender);
            map.put("GenderPrimaryKey", gender._getPrimarykey());
        }
        Title title = new Title();
        title.setTitles("zZ64Ip9YDEOFUVaEFjiJ55m3XGpdXIkZrleJ2UtaNIHals8mb8");
        Title TitleTest = new Title();
        if (isSave) {
            TitleTest = titleRepository.save(title);
            map.put("TitlePrimaryKey", title._getPrimarykey());
        }
        Language language = new Language();
        language.setLanguageDescription("m3hSrDo4Kuglw952jFqlGYnUZGJFXu9T4nMbJWTeDD4vzlCaMl");
        language.setAlpha3("5yd");
        language.setLanguageType("I4ZkpzO2Lfie6YmCzE339qSTgLnNhBk5");
        language.setLanguage("t3cHe44RUHD15KDtLD6dJ5PCmTcYEhUnJPjhrw6yyfqFAEzMUo");
        language.setLanguageIcon("A42Sl09OJOAAfnm95UE8vbaTuqRHs74fRne04FBe7AhKW5yo6z");
        language.setAlpha4("ImqA");
        language.setAlpha2("Mf");
        language.setAlpha4parentid(6);
        Language LanguageTest = new Language();
        if (isSave) {
            LanguageTest = languageRepository.save(language);
            map.put("LanguagePrimaryKey", language._getPrimarykey());
        }
        CoreContacts corecontacts = new CoreContacts();
        corecontacts.setNativeMiddleName("iusJd1ZUOIeHRTs3RASDVjVxVqFBeydNXtCt2klwtT1raOIhM6");
        corecontacts.setAge(115);
        corecontacts.setNativeFirstName("NFnmrFuIkOjzcFyhF2fVFpiuCaa3apI55jTwNOL1c9OlBDx91s");
        corecontacts.setEmailId("vvApI9fyK7VfEqEbSF3HhfovwlHLUeURcNNZ9K49cZtv1gWdPz");
        corecontacts.setPhoneNumber("35YH10mwaoUAQrDvQa6m");
        timezone.setTimeZoneId(null);
        corecontacts.setTimezone(isSave ? timezoneRepository.save(timezone) : timezone);
        if (isSave) {
            map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        }
        corecontacts.setMiddleName("gY4uUBjgOceUUNt6c29bSHD2Omj5uFynzy2NWjESfrZrv8RaQF");
        corecontacts.setGenderId((java.lang.String) GenderTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setDateofbirth(new java.sql.Timestamp(1465803209030l));
        corecontacts.setTitleId((java.lang.String) TitleTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setNativeTitle("Kpyy2OHTJcpAJZETcqcbQH5ba77XTIzRnt3bBBJOSgs1443MCS");
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1465803209197l));
        corecontacts.setFirstName("LuNmQKCVRsi0gYfMMUnTUGQ5oX4QUV4gIvPxcfJ6Nv52GigJSN");
        corecontacts.setNativeLanguageCode((java.lang.String) LanguageTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setNativeLastName("lhHQZS2vSnZnFEASanXEqbM3nBaI8Y6IDAf5uBSJurql1jzC2K");
        corecontacts.setLastName("2rcXB6ATfTKH5m00x1KbGU1DgyHQKqVPGFpIfbKM9YHBpfYcOr");
        java.util.List<CommunicationData> listOfCommunicationData = new java.util.ArrayList<CommunicationData>();
        CommunicationData communicationdata = new CommunicationData();
        communicationdata.setCommData("juBeeEDo3L");
        CommunicationGroup communicationgroup = new CommunicationGroup();
        communicationgroup.setCommGroupName("z3YfoUW2r6gXBwq956Noq8XD2pbQW12VczBPuysLJXqI1JnLmB");
        communicationgroup.setCommGroupDescription("5poirmqDdsb4GFwIfbFQXfuIf5HseUd3IvqT56lgcoBVXM39kK");
        CommunicationGroup CommunicationGroupTest = new CommunicationGroup();
        if (isSave) {
            CommunicationGroupTest = communicationgroupRepository.save(communicationgroup);
            map.put("CommunicationGroupPrimaryKey", communicationgroup._getPrimarykey());
        }
        CommunicationType communicationtype = new CommunicationType();
        communicationtype.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationtype.setCommTypeName("oSfmMEd5pbcvKxXJfsdP7qIDfvTXz9m8PIaUX8dkKhl9vQMBES");
        communicationtype.setCommTypeDescription("Dte8zdxDtPkQ6ON2yRr7nvtFOOkvBaWeIG0ohFsvfW0xRzU8hl");
        CommunicationType CommunicationTypeTest = new CommunicationType();
        if (isSave) {
            CommunicationTypeTest = communicationtypeRepository.save(communicationtype);
            map.put("CommunicationTypePrimaryKey", communicationtype._getPrimarykey());
        }
        communicationdata.setCommData("b4BVQLxIIf");
        communicationdata.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationdata.setCommType((java.lang.String) CommunicationTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        listOfCommunicationData.add(communicationdata);
        corecontacts.addAllCommunicationData(listOfCommunicationData);
        java.util.List<Address> listOfAddress = new java.util.ArrayList<Address>();
        Address address = new Address();
        address.setAddress3("06FlABy2dxxDgptdvhZ2J3Rw5aA7Pt3vSJCQVTZLG3P1NheLQs");
        address.setLatitude("azFk4hXvRLwZCBYxXVXolBp0zDXhrXX3WH0EMSNVOonGXkOZ6Z");
        City city = new City();
        city.setCityCodeChar2("Q0TWIExjEdsnK6dzfbS3lkDLLa10pmwE");
        city.setCityLatitude(6);
        city.setCityName("Vgs3A4ZYFIQEOl2DJeSHJtz472ttfIfKGndnszsFiOt6XKH0xh");
        city.setCityDescription("cW1CNbdOQFwYyWCU9L1WAfaihXnVgNBs0F5ODmvbcCBakwhgqU");
        city.setCityCode(2);
        Country country = new Country();
        country.setCurrencyName("owPLoh4H54TXiZH4PK2NASPxsyS8Zr8MTTJEP7AUhMuS575U1K");
        country.setCapitalLatitude(4);
        country.setCountryName("R7XmukaBopKCaQjAinOKxP1f3Xl6rDkA714Z1JT58Y1AtAflFX");
        country.setIsoNumeric(916);
        country.setCurrencySymbol("9Ba3rV7TxSv0GGsElSMgXOGnNNO4j90a");
        country.setCurrencyCode("Liw");
        country.setCapital("0XyRfjiHMSQU75nRiDxaJ6lpjqGZTBW5");
        country.setCountryCode2("mPc");
        country.setCountryFlag("drZ6gDWLwKiYPHm8CJJuc38sthE0JjBwb8y1ND7zBUi1X2iZ59");
        country.setCapitalLongitude(2);
        country.setCountryCode1("86I");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        State state = new State();
        state.setStateFlag("3A8CW7YWJVr5mRIj54Rd13OMk7cHWRxSyFrRkWBPlowK2jtPPJ");
        state.setStateCapital("2Gfe26sEShQ6vNx5W2eqwVGSeE60aIASlVYsvNU4IhNGFDmDJ2");
        state.setStateDescription("5hTdp0GoowY4pCtmSs1JMGFEqqzsqPt5Eocx7dXCFXsvxhFOd4");
        state.setStateCode(2);
        state.setStateCodeChar3("mWPzGT9lzk2LNtdSoXEXXoPOxNRlf5ku");
        state.setStateFlag("5MaBsJdnZppeeffp4TvQzFbl3yXqRadxciwgyCHyIL4bs6Phaz");
        state.setStateCapital("YB0M0xEygir1YGV3MDqxePjCGEQ3rlyJbnCeBEGc93yDL3fHQZ");
        state.setStateDescription("SXpnUwQDDzPn7vFCOsWOGiPcA2gu8qBHt7a2jHKv58r3u75UtO");
        state.setStateCode(1);
        state.setStateCodeChar3("cCBjVT5lS6AVaZuxCgOSpNw2ZWBr1Mn2");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateName("xUuvsnQ4STUXIr4crYfvsSrOGfldKLFF0pHRMT4IoSyUykSw18");
        state.setStateCodeChar2("L1vmmnwot01bkk6IivqhyYtnqPn1KKM4");
        state.setStateCapitalLongitude(2);
        state.setStateCapitalLatitude(5);
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        city.setCityCodeChar2("f0NcSrOzpMKpxt3obziC5rDXOSxM6I4H");
        city.setCityLatitude(5);
        city.setCityName("ptzSao5HWBhT5Gii4GdWvknpJUPEg1dgS2q3eALZVmuxRols1w");
        city.setCityDescription("K4jPL033YLHfuSZlM6JgPjWG1HdMqyf1vIBhGpfTZGzVsYv2mo");
        city.setCityCode(2);
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityFlag("egUxobHM9UsU59h4i1NfAge51WmUE6QFZ4LH1XYzt2P5GtxlBa");
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityLongitude(8);
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        AddressType addresstype = new AddressType();
        addresstype.setAddressType("C6eHgxXudk2IKx6Ae1edNUwjA5dnqkLRRzGyrjeenCDZwn6a0R");
        addresstype.setAddressTypeIcon("1NTTyBCwHDaIdTwNbZTm6Mz4L6ck6kCTHJ4KNGWy9Eqe5i3bt1");
        addresstype.setAddressTypeDesc("c1SJjrnGH83ZwSXe0oET9OIiOSiAFFzexTYcB3jrSgYrhb7Izq");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        address.setAddress3("SkUDyYZZLH5RXpNc8sqfUNweXY84t7UECr1drmXJjBoRMZhmKD");
        address.setLatitude("Qu0Fqkrr1B9E6D4nArh3pQqk49VOa4TKmyYnSVYWRunQALE4NU");
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setZipcode("I0LDF4");
        address.setLongitude("JBKo7pG0PTreX0Rb50FRlgE3KforkeqNPD6OTqYhegcjLPButs");
        address.setAddress2("vMxl1Wy8GWLaZht8xAM41qCPUysCqcvki1kyYik9A5g2xmHDTP");
        address.setAddressLabel("Yf2qx142FP5");
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress1("4tpIEQE5mGo8f2A7i3s8L61TyNkKftZ8F0c8DFtHXXFwvvdOkf");
        address.setStateId((java.lang.String) StateTest._getPrimarykey());
        listOfAddress.add(address);
        corecontacts.addAllAddress(listOfAddress);
        corecontacts.setEntityValidator(entityValidator);
        return corecontacts;
    }

    @Test
    public void test1Save() {
        try {
            CoreContacts corecontacts = createCoreContacts(true);
            corecontacts.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            corecontacts.isValid();
            corecontactsRepository.save(corecontacts);
            map.put("CoreContactsPrimaryKey", corecontacts._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private TimezoneRepository<Timezone> timezoneRepository;

    @Autowired
    private GenderRepository<Gender> genderRepository;

    @Autowired
    private TitleRepository<Title> titleRepository;

    @Autowired
    private LanguageRepository<Language> languageRepository;

    @Autowired
    private CommunicationGroupRepository<CommunicationGroup> communicationgroupRepository;

    @Autowired
    private CommunicationTypeRepository<CommunicationType> communicationtypeRepository;

    @Autowired
    private AddressRepository<Address> addressRepository;

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
            org.junit.Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            CoreContacts corecontacts = corecontactsRepository.findById((java.lang.String) map.get("CoreContactsPrimaryKey"));
            corecontacts.setVersionId(1);
            corecontacts.setNativeMiddleName("SU6lYZCoyaEECdDKsm8U8c3roL0kou2Ds5pJPG2cM3ghSmFJd3");
            corecontacts.setAge(51);
            corecontacts.setNativeFirstName("IlRi8Ck7DLpwe1Beu4Lbqg6q5wnBsr4rvmFNwzgIwQ2srmi1LO");
            corecontacts.setEmailId("vo4jOh6SJ644Td1jzGT6xfr4UUKv1cksJ3zkDVelmg8qMAflTf");
            corecontacts.setPhoneNumber("nNdyhlTWNuQNcUsjEuDp");
            corecontacts.setMiddleName("X99hnLKVBkQvwkqnKr4awoui6uQWgruTH8z2lXXToKYhrvfw8T");
            corecontacts.setDateofbirth(new java.sql.Timestamp(1465803211498l));
            corecontacts.setNativeTitle("pRm8irAkzfxFBlzbUyAl6WTI9yoiLNgV498sXJUlB8XXYYHOTv");
            corecontacts.setApproximateDOB(new java.sql.Timestamp(1465803211597l));
            corecontacts.setFirstName("FxZPr4jvsp8DpZg1ldEiSFM6CKGWNnu83HfqPkKa0Q8YJaLUV6");
            corecontacts.setNativeLastName("4krALAAnwlJZEYiAUHA22I0yOgEcc02gaUAngmpjtpBadAIvvO");
            corecontacts.setLastName("7r1sA7NRRHwsNNyhzeclmcL4R1q7OmL0DkfttbyNwJZsPf8lBH");
            corecontacts.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            corecontactsRepository.update(corecontacts);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            corecontactsRepository.findById((java.lang.String) map.get("CoreContactsPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBygenderId() {
        try {
            java.util.List<CoreContacts> listofgenderId = corecontactsRepository.findByGenderId((java.lang.String) map.get("GenderPrimaryKey"));
            if (listofgenderId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBytitleId() {
        try {
            java.util.List<CoreContacts> listoftitleId = corecontactsRepository.findByTitleId((java.lang.String) map.get("TitlePrimaryKey"));
            if (listoftitleId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBynativeLanguageCode() {
        try {
            java.util.List<CoreContacts> listofnativeLanguageCode = corecontactsRepository.findByNativeLanguageCode((java.lang.String) map.get("LanguagePrimaryKey"));
            if (listofnativeLanguageCode.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            corecontactsRepository.delete((java.lang.String) map.get("CoreContactsPrimaryKey")); /* Deleting refrenced data */
            addresstypeRepository.delete((java.lang.String) map.get("AddressTypePrimaryKey")); /* Deleting refrenced data */
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey")); /* Deleting refrenced data */
            communicationtypeRepository.delete((java.lang.String) map.get("CommunicationTypePrimaryKey")); /* Deleting refrenced data */
            communicationgroupRepository.delete((java.lang.String) map.get("CommunicationGroupPrimaryKey")); /* Deleting refrenced data */
            languageRepository.delete((java.lang.String) map.get("LanguagePrimaryKey")); /* Deleting refrenced data */
            titleRepository.delete((java.lang.String) map.get("TitlePrimaryKey")); /* Deleting refrenced data */
            genderRepository.delete((java.lang.String) map.get("GenderPrimaryKey")); /* Deleting refrenced data */
            timezoneRepository.delete((java.lang.String) map.get("TimezonePrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateCoreContacts(EntityTestCriteria contraints, CoreContacts corecontacts) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            corecontacts.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            corecontacts.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            corecontacts.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            corecontactsRepository.save(corecontacts);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "firstName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "firstName", "etXD0MVYjGR6pVd4aCuFvMfp5u4uDTUNqCtTfE9eksMcKZt9QrH8hBbRmcslZJOEZwbkLEHA1LPZhsafbVNmkAZfWqyuhtmI31fCvuftMO8mLU9MrXPMHxhk9xDfBVqSOkEw0olHjyUXvipNkGz7SfzY5HGTqCTYOdxysAeasv6ogZx76l7m0bgWyLVnoqI0rJwuPjCfDMg4jHU96QU5KfECI2iqePv5YD9kw33O1NgE33RlSYxLrUW0sj4DprwZt"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "middleName", "eP6A80vmv4VOMBNVYIBFuXaVNckkg5M9gskYUAF1w6CnoBVu1UDaJIKlIsjUJDvLEHUaHhk2YadMOdrVWu2JQ4VHLG5q8Yoj6BIVP18BW5cI5sozN6xRv8aZRg2LNEVt4W44CgBJoAO9TMrD5kDIHlBaXQoMkpqbAp2aBx6IJC9m4PztapwkbO9U4uTCTvhAnoW3knPHnph2uiMb6EKe3eJ0oDCRiAhP3q1cJacXA5wI3hKxXXy1V3GGAca2x3tL7"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 4, "lastName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "lastName", "MH5tMW6XEX3uBftZ07UIaAMj3Axr2JRXWSfAm1Sy7DunL4ARG8BKRx0cUIHG1xZdgZ3MSnRkPLeMr8TBW2BhuBEk714MVHXAHROH8MVZJH8z94yaz38J43s5SEVK499e4ur7G36Muex9BOlBj8meQhOsjHsqOOC2F9smQNSSNjo0yEWsS4MUyRSWNDyFhvdMrnAe0GZvlmuXzUYKBt02ogcoPvDVrBqOjxPkjkLGB2w72d7RN86ytsyZpzywWK8QD"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "nativeTitle", "oU6lVBqDA9nvVN7JYRWDHlofq0cMqHv1KmWIhV2MAxg6cTTnTYuCg7HONqU6TBX8b"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "nativeFirstName", "Yqo54zXZXEcPHExekgpznJZORMdCgpdGkUyns76YBgEG2QkqMFbofJBYcN6AVOgQpHzxsPfkbGm5yR8nVdEceW8MqfyOSbTgijpqcxLPgck0lJEwM2UH6BMVtAu2QuLrKibSKhtEWy5arU5cruS3kGCDYdu6lgddeU3H3VGC1TEmeULvfnEjLcBxfE9ixfsnhqF2F74XCXPncOZ4qXeZDIROxr1mthymDA4fXUbMe20rJGZOpLqB8fAKw6qNrWDWe"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "nativeMiddleName", "7AWjGSVWUNE3zKwmLmHwan65DxY6cZTQQkU1QsBPTfcrLcYSj9HLQV0uaQVCmiGJXeVbzjj6e7uraubfZqvNzx36zqelOcT6gE5RCFA89BzyyFSqHaXUSluxZIlv1YqGgp0EOFl1cNy41l5NcyezC7sQqp0ZkUb0Ze7kh6WebqbLdNp7IsvrQsMkmUKvsXcR1bNKESrzWc0QxTEZH8TWx9MgWqH6KGBuyIvkwUn7xhfH2VnMdfhXhE5HviVvY3oZS"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "nativeLastName", "wPGUnbgmfq9KEEBwrUFHv9EZljivqp79rNn97sPKEoR3X9DbMbmSGPg3MDQmIkjwKlZDYFITOu3AfW1GFlPMhgoFmlY1WZ67IovHTImnJPDHSedLO3i0qypBBCi5cg2eWhUiBwXdo7ZRwecuNLzKCPXkrUmkJ7XDQZAh6c6I6inXXdqBYpGNgJS6tDWBSpuCSm6npSYyKoPXxXShlEpPOApqbgEqvbNEAAFIRFk2nw3ou8bEquCr09Wf5TVd8JK7e"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 10, "age", 244));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 11, "emailId", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 12, "emailId", "JVN2kRq8FEgr7GyD4nM6l9OSgAvuCTDtJ6ZONI0voDN2apf7WgOQVUwWSJqAuIoTR8NkXgHCteTsxj5nmNEG3Obr5NFqKyE1tT8jFEjU48Cge9WWWkXJIRqSrdEfTpE51koZBBa2fWLOLIKHWV40zzzq7PG6udgtGiQLWnSydUS5g7UhxMAzAtGXvog98pWIW35KhD1ASNM9bLqSyRHWAu75XEwWzmQpeskmAPJncZcMSHUfvRKgUDnZpNDONeKqy"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 13, "phoneNumber", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 14, "phoneNumber", "CgdAsUbyAzoRaFCe8zWHD"));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                CoreContacts corecontacts = createCoreContacts(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = corecontacts.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 2:
                        corecontacts.setFirstName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 3:
                        corecontacts.setMiddleName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 4:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 5:
                        corecontacts.setLastName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 6:
                        corecontacts.setNativeTitle(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 7:
                        corecontacts.setNativeFirstName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 8:
                        corecontacts.setNativeMiddleName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 9:
                        corecontacts.setNativeLastName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 10:
                        corecontacts.setAge(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 11:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 12:
                        corecontacts.setEmailId(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 13:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 14:
                        corecontacts.setPhoneNumber(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
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
