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
import com.app.server.repository.appbasicsetup.usermanagement.LoginRepository;
import com.app.shared.appbasicsetup.usermanagement.Login;
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
import com.app.shared.organization.contactmanagement.CoreContacts;
import com.app.server.repository.organization.contactmanagement.CoreContactsRepository;
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
import com.app.shared.appbasicsetup.usermanagement.User;
import com.app.server.repository.appbasicsetup.usermanagement.UserRepository;
import com.app.shared.appbasicsetup.usermanagement.UserAccessLevel;
import com.app.server.repository.appbasicsetup.usermanagement.UserAccessLevelRepository;
import com.app.shared.appbasicsetup.usermanagement.UserAccessDomain;
import com.app.server.repository.appbasicsetup.usermanagement.UserAccessDomainRepository;
import com.app.shared.appbasicsetup.usermanagement.PassRecovery;
import com.app.shared.appbasicsetup.usermanagement.Question;
import com.app.server.repository.appbasicsetup.usermanagement.QuestionRepository;
import com.app.shared.appbasicsetup.usermanagement.UserData;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class LoginTestCase extends EntityTestCriteria {

    @Autowired
    private LoginRepository<Login> loginRepository;

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

    private Login createLogin(Boolean isSave) throws Exception {
        CoreContacts corecontacts = new CoreContacts();
        corecontacts.setNativeMiddleName("oTrJB3ssrn8JVXqfNaf00FkZlOypUagXMCWfDpWkUNW5iOSgj2");
        corecontacts.setAge(33);
        corecontacts.setNativeFirstName("Lo0Qaf9gYlHn2iZTy4INss60F82vVjJYJKhZUGXiX2mjV1EHZk");
        corecontacts.setEmailId("ruRITvePlhx8Dcp09saPZ6armADHVRMDveXJ57zGr7jNf9Wjyl");
        corecontacts.setPhoneNumber("1UDdxxgOBLWXzjeZAU3M");
        Timezone timezone = new Timezone();
        timezone.setTimeZoneLabel("KjwRQWyhtMGb8xt2Tn6r76DYwitwh3N0a7sMy7HoFnCYk3wUoX");
        timezone.setUtcdifference(4);
        timezone.setCountry("2HRDMM8A4HH15xBQxq1S4PISSrBr2ua5Pmn3ZrTSCGBfSAGqzu");
        timezone.setCities("QtQ0erQA0UwrjFQ9oyEi6VdpDnjCJNYXhsWf05cXOoJeJFjGtJ");
        timezone.setGmtLabel("WOVlBV8XaVEEC9ELDuE2QMFA9G7OzX2Zyssu6W3AAWuZRxcZal");
        Gender gender = new Gender();
        gender.setGender("PnJH6hhDJrQeymqMyMGt8ltouDhA6HqY6BtzszDldjP4OOJcuP");
        Gender GenderTest = new Gender();
        if (isSave) {
            GenderTest = genderRepository.save(gender);
            map.put("GenderPrimaryKey", gender._getPrimarykey());
        }
        Title title = new Title();
        title.setTitles("RGdKQZK8DnvI1e8Ydz24ic7N4KtRCZOXkeBg1GHcQAVIWB1ybl");
        Title TitleTest = new Title();
        if (isSave) {
            TitleTest = titleRepository.save(title);
            map.put("TitlePrimaryKey", title._getPrimarykey());
        }
        Language language = new Language();
        language.setLanguageDescription("oXwSykLbwWTNBSp3XEtXvd60oYuhhoPfrzr87RAm0Zf5P1DBUw");
        language.setAlpha3("XMC");
        language.setLanguageType("fZdrnDfxq8EKJfwf2giYoDJeFSHZcTbk");
        language.setLanguage("sHEOuiEn7UQXCK7IniZD21zcbgBCLR2idbR9OT7BrSHuld22zk");
        language.setLanguageIcon("1FrjL2HbgdeEni2jMq81020H09n9ZEgKMeymktDsomLNGJOlnp");
        language.setAlpha4("FuGQ");
        language.setAlpha2("Cv");
        language.setAlpha4parentid(3);
        Language LanguageTest = new Language();
        if (isSave) {
            LanguageTest = languageRepository.save(language);
            map.put("LanguagePrimaryKey", language._getPrimarykey());
        }
        corecontacts.setNativeMiddleName("KVEvPpFgNvJszIkiHdi0TEz83XKOlFpRv3l1velJpV5yKYbbwT");
        corecontacts.setAge(21);
        corecontacts.setNativeFirstName("k6AFwfeJOj4PkPEMXhCHPBhQ0lgHKVH4rYALjiPN4cUMBquzKH");
        corecontacts.setEmailId("ozOtVCWxJOYRKUDHcsIcaBaGhFcFpWQCoOwTRrw9X2w8KhstEB");
        corecontacts.setPhoneNumber("9ipJH1vkXfvJ5GFtky8w");
        timezone.setTimeZoneId(null);
        corecontacts.setTimezone(isSave ? timezoneRepository.save(timezone) : timezone);
        if (isSave) {
            map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        }
        corecontacts.setMiddleName("GAeHp1WpjiKh3Rz6mEYbDxLkFy4SkNoLV6mLzVtiDbcijoD3D0");
        corecontacts.setGenderId((java.lang.String) GenderTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setDateofbirth(new java.sql.Timestamp(1465803244825l));
        corecontacts.setTitleId((java.lang.String) TitleTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setNativeTitle("Rk2mJpfhN3yHVb3sMxysAAvUtlKPqUCWmEPI6GtdZUhghKLP5U");
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1465803244993l));
        corecontacts.setFirstName("IPDMUCwX2SBBGLgO6WhpIFzxs0fWx8wilr8dDZBPKGdy2Pw21N");
        corecontacts.setNativeLanguageCode((java.lang.String) LanguageTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setNativeLastName("EldiLr6XKUUVoMDVl3DDgnBEDuxvoarMPRTSn57PgXmYVWvec4");
        corecontacts.setLastName("XIuta7V9TI0QY5HzWP7oJe7x79h5sMh8N6NFx4qgUrvcVMIoJT");
        java.util.List<CommunicationData> listOfCommunicationData = new java.util.ArrayList<CommunicationData>();
        CommunicationData communicationdata = new CommunicationData();
        communicationdata.setCommData("uZGPBga5We");
        CommunicationGroup communicationgroup = new CommunicationGroup();
        communicationgroup.setCommGroupName("ANv4q1Ycu7xMkXP1cTT1q17KGgzhFZ3jvkVklH5jKkVJans2Uf");
        communicationgroup.setCommGroupDescription("LsLH3QFXZQ8hz4Lg9CP6KBeWNL5nBAj2hcxXM6rTZ3c1tWk9Qn");
        CommunicationGroup CommunicationGroupTest = new CommunicationGroup();
        if (isSave) {
            CommunicationGroupTest = communicationgroupRepository.save(communicationgroup);
            map.put("CommunicationGroupPrimaryKey", communicationgroup._getPrimarykey());
        }
        CommunicationType communicationtype = new CommunicationType();
        communicationtype.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationtype.setCommTypeName("315ZjNouSolQZpalxk1JLGGym8wsxGsg319XH9NGWuNSVBTeL4");
        communicationtype.setCommTypeDescription("sE9CRK5pbJBnEL0Ck4qcZENuTesmC6dIPp3b4X91wDBDh3jUQh");
        CommunicationType CommunicationTypeTest = new CommunicationType();
        if (isSave) {
            CommunicationTypeTest = communicationtypeRepository.save(communicationtype);
            map.put("CommunicationTypePrimaryKey", communicationtype._getPrimarykey());
        }
        communicationdata.setCommData("Uy6jco3oJv");
        communicationdata.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationdata.setCommType((java.lang.String) CommunicationTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        listOfCommunicationData.add(communicationdata);
        corecontacts.addAllCommunicationData(listOfCommunicationData);
        java.util.List<Address> listOfAddress = new java.util.ArrayList<Address>();
        Address address = new Address();
        address.setAddress3("2kNXwWAiEZO3MrqQeb8Wr78y8ErItgneJ7ymJlSIBAYkdszqkO");
        address.setLatitude("0WKjtfJvz7IC1nU5Nf1SpErrtPiqBKdmxrJtuaxNeBu63yKoMC");
        City city = new City();
        city.setCityCodeChar2("O38dEoH5jytek7xvE3EymVvYmQFx1sKm");
        city.setCityLatitude(6);
        city.setCityName("sU0mfO1mZxozLzRpA6goqKo04sipYcvhp1RhsrAZKyvnzRMc9O");
        city.setCityDescription("AlyukLa28onR8XVA0aCK2eoJSQPN4sEQP45sD25yUsJcKSTPdk");
        city.setCityCode(2);
        Country country = new Country();
        country.setCurrencyName("z7cjsCLmvO4OhpFQP37XRGANWxYzGk4DQyCO2lchd2yLxQSQYJ");
        country.setCapitalLatitude(4);
        country.setCountryName("MQvis0kr813LZmtyaWzlvEF0zKQgt1K9rfqguOeyrupJjz02LR");
        country.setIsoNumeric(200);
        country.setCurrencySymbol("K5I8lqRCz7CJpncIfb3P1RGoM5Ma7QcJ");
        country.setCurrencyCode("ah4");
        country.setCapital("LioFOfgdgt9UiJwYpSnj0dHLbm8vKXSW");
        country.setCountryCode2("WAe");
        country.setCountryFlag("m8TlvVhBgUDwTXUQMJD9WPUvzU8MGBbaPhPRelx9b9T0zBEIhj");
        country.setCapitalLongitude(10);
        country.setCountryCode1("LsH");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        State state = new State();
        state.setStateFlag("XBZ1UiD6hLPc24IpvNFNpWo5udf6gnsQy3OKWJf937FrTnYt1N");
        state.setStateCapital("49IFcbszNgwvszApDkcofqIVcu3ovNr5PnskoIExFSRlIu0SNl");
        state.setStateDescription("D9YIwS9qREA0Sx59HUdAomqMUPFFsBp8E8ChLQaaqilUGAy3zM");
        state.setStateCode(2);
        state.setStateCodeChar3("pJ80dH84qk5YaePf3Whif0KvrRTVAhnp");
        state.setStateFlag("CWbSniqYRF7aMoB0dbLXyLIDeUuRt70uxP1MpbJIyWSeDROmde");
        state.setStateCapital("BhpYM7QnHqFVxlvyzoTTCJMiw3NvlrWWU9ochzWuZPotFfcQP6");
        state.setStateDescription("CjCveKxsaQq92BoqtZHbpY66lvsy8Pkyi6xuYY3WQriGm9Ofyk");
        state.setStateCode(2);
        state.setStateCodeChar3("rXD3wBDIWMolidWWxlEQKyUoiu6eOpl2");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateName("6sSpPEltat4aP71itW08KCq7BHmiyxNnzgvHNqTOGUcfBt1lXH");
        state.setStateCodeChar2("19nVFQcLxC4kayZRKla7BaVJqGZEdzHa");
        state.setStateCapitalLongitude(4);
        state.setStateCapitalLatitude(3);
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        city.setCityCodeChar2("WuWy6HqVQuxqxXStErn3nlTxM99x02gp");
        city.setCityLatitude(1);
        city.setCityName("LZXS66ryhVHDMdA8pkybY7SvZ1vRvwSu0C5K4FFT0faKS3LVog");
        city.setCityDescription("n6NF5lL4CyOlOkXLpqPMez09qepKyvOWGiIlNWujp2AU0Tml0s");
        city.setCityCode(3);
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityFlag("BnI9Q06JsNE55iecYnLS6XrWVvz1A9pIXdhoiT2d0VKeFDVicv");
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityLongitude(5);
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        AddressType addresstype = new AddressType();
        addresstype.setAddressType("o0mWnElXDV9HJwC1RgHiHwYmTysAN7Dc4dugkMnrxQuDWlfBag");
        addresstype.setAddressTypeIcon("ermy7HQKJuWJWIrTIkPdgTZ6svUNFQmQ42Tf4uta51XVdXqSZG");
        addresstype.setAddressTypeDesc("w2VNLQVNXmZy6PdBIjwTf8aKK83RHCKXLWy347d4hb3kJf2ZON");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        address.setAddress3("5AEiLcquMGCAyBygIwzLpSz4hBbiBW42tyXgHf7dK148Rvvx1I");
        address.setLatitude("NFZoBF63lcrf4Umf12buhahW1IMw7vgJ7FBTNuzDX6wj68p8dW");
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setZipcode("uhZEgY");
        address.setLongitude("0rPJkkr79dY3Wwf5vO0VGfbpGRTuq3ja8HH9m8lBYGxpiJkkjN");
        address.setAddress2("91B90Ti64FGnxvPhjAKjKlt9dnI47gX6ym9MRiSdXgLtd7WMvE");
        address.setAddressLabel("uUuWdd5Yrpn");
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress1("3oagfVllbQxClHTz6qNMdnz4exthHY9dqCA49sNKz9vV5U0M54");
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        listOfAddress.add(address);
        corecontacts.addAllAddress(listOfAddress);
        User user = new User();
        user.setAllowMultipleLogin(1);
        user.setPasswordAlgo("w07c5VzxVdXduQ9A1XAsPu6tNnRzhPnKHCJdcMLvcaXflK3UUL");
        user.setSessionTimeout(2095);
        user.setMultiFactorAuthEnabled(1);
        user.setChangePasswordNextLogin(1);
        user.setGenTempOneTimePassword(1);
        UserAccessLevel useraccesslevel = new UserAccessLevel();
        useraccesslevel.setUserAccessLevel(valueGenerator.getRandomInteger(99999, 0));
        useraccesslevel.setLevelDescription("HkHyS3nRfRy04XfihuVwgd7VBhGCs60onN0Rr3EN49AOYpfn3a");
        useraccesslevel.setLevelIcon("O7apL5mIwXP9u4oGPSsAzdGo0e9XTQ48XKulGkkpVhOaHoP3lb");
        useraccesslevel.setLevelName("734NA0796UV0gXyNvYCB1mw4KXolFcHxOXcgOhxcbD34Pmo4bq");
        useraccesslevel.setLevelHelp("P16jxxSFW2yVfjrfqQ9FTp5J6linqczhjL3IJyAW9T2JYnV8C0");
        UserAccessLevel UserAccessLevelTest = new UserAccessLevel();
        if (isSave) {
            UserAccessLevelTest = useraccesslevelRepository.save(useraccesslevel);
            map.put("UserAccessLevelPrimaryKey", useraccesslevel._getPrimarykey());
        }
        UserAccessDomain useraccessdomain = new UserAccessDomain();
        useraccessdomain.setDomainDescription("UQKEH5AfLFEk4EHmiAgRNNEUJK1OitpcGmwyRC4K60dgCdJbQ3");
        useraccessdomain.setDomainName("XgCd3PlzzVM3p0tBAeOIRqw3dsAkExnyt2zZuJFy9yGbJvZsRr");
        useraccessdomain.setDomainIcon("f8yl9eMTpcVAVv4EBmlH3sJhvtC7EC0BKoY9xgcWDCztrKP7pz");
        useraccessdomain.setUserAccessDomain(valueGenerator.getRandomInteger(99999, 0));
        useraccessdomain.setDomainHelp("z5wTIpSeJ5DRS8EfTmNx7DXrFE7jhkwlORahd96ygCL04Na3oj");
        UserAccessDomain UserAccessDomainTest = new UserAccessDomain();
        if (isSave) {
            UserAccessDomainTest = useraccessdomainRepository.save(useraccessdomain);
            map.put("UserAccessDomainPrimaryKey", useraccessdomain._getPrimarykey());
        }
        user.setAllowMultipleLogin(1);
        user.setPasswordAlgo("cEDaB8cqX8sqJQTlHosBnlgPBxuAeC6eLgp0nU7dnhPOVdA0mq");
        user.setSessionTimeout(338);
        user.setMultiFactorAuthEnabled(1);
        user.setChangePasswordNextLogin(1);
        user.setGenTempOneTimePassword(1);
        user.setUserAccessLevelId((java.lang.String) UserAccessLevelTest._getPrimarykey()); /* ******Adding refrenced table data */
        user.setIsDeleted(1);
        user.setLastPasswordChangeDate(new java.sql.Timestamp(1465803247187l));
        user.setUserAccessDomainId((java.lang.String) UserAccessDomainTest._getPrimarykey()); /* ******Adding refrenced table data */
        user.setPasswordExpiryDate(new java.sql.Timestamp(1465803247340l));
        user.setIsLocked(1);
        user.setUserAccessCode(43289);
        java.util.List<PassRecovery> listOfPassRecovery = new java.util.ArrayList<PassRecovery>();
        PassRecovery passrecovery = new PassRecovery();
        passrecovery.setAnswer("D9ufEkjB37r5KrTihAPJtdMStX0zEfJCZsbTCKcx2kEnRqr4VY");
        Question question = new Question();
        question.setQuestionDetails("peVv7bPNpb");
        question.setLevelid(5);
        question.setQuestion("6v7XLNkKKbFBSgMAHuVNwYbNlRFw2NRoAmXWQF58Qcy2av58QS");
        question.setQuestionIcon("Ue75p9lbP4vsrHc4gyC25COQvjE4oHsv14S7ej9SYFdroO4tA3");
        Question QuestionTest = new Question();
        if (isSave) {
            QuestionTest = questionRepository.save(question);
            map.put("QuestionPrimaryKey", question._getPrimarykey());
        }
        passrecovery.setAnswer("3FhKf5Bwn43LyFIMNJAe6mMVzwTPD7w9MOdZTRWsqFArnB6JR0");
        passrecovery.setQuestionId((java.lang.String) QuestionTest._getPrimarykey());
        passrecovery.setUser(user);
        listOfPassRecovery.add(passrecovery);
        user.addAllPassRecovery(listOfPassRecovery);
        UserData userdata = new UserData();
        userdata.setOneTimePasswordExpiry(2);
        userdata.setPassword("wIGrC3QUV3ywzfCRgAXLe5fX8Sh4CzVNMfDAQjRjOAD47GLtam");
        userdata.setOneTimePasswordExpiry(6);
        userdata.setPassword("AYLYMyp6hLBG9KHP7gyXbAGZGlVNgSTU6Q9B9WQYcovoRWOAKO");
        userdata.setUser(user);
        userdata.setLast5Passwords("6YmzoC8VbK0CWVyEJ2iFRjPOl5Vb0PRwrcXA3bntr3oo1DGK3l");
        userdata.setOneTimePassword("MLHgNsM4OiEOfotpy4d5Q15FUOJUbvM8");
        userdata.setOneTimePasswordGenDate(new java.sql.Timestamp(1465803248369l));
        user.setUserData(userdata);
        Login login = new Login();
        corecontacts.setContactId(null);
        login.setCoreContacts(corecontacts);
        user.setUserId(null);
        login.setUser(user);
        login.setFailedLoginAttempts(9);
        login.setLoginId("qNRFEqqP2ZRic9SMTZz0srJ4AfSfnkuHVCPgXO5QFJaA7H0Ij4");
        login.setServerAuthText("E5vcvGKgwXDivx8E");
        login.setServerAuthImage("rFNzSEun3Qre5fwtd5eSZY8lttyUQWlS");
        login.setIsAuthenticated(true);
        login.setEntityValidator(entityValidator);
        return login;
    }

    @Test
    public void test1Save() {
        try {
            Login login = createLogin(true);
            login.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            login.isValid();
            loginRepository.save(login);
            map.put("LoginPrimaryKey", login._getPrimarykey());
            map.put("CoreContactsPrimaryKey", login.getCoreContacts()._getPrimarykey());
            map.put("UserPrimaryKey", login.getUser()._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private CoreContactsRepository<CoreContacts> corecontactsRepository;

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

    @Autowired
    private UserRepository<User> userRepository;

    @Autowired
    private UserAccessLevelRepository<UserAccessLevel> useraccesslevelRepository;

    @Autowired
    private UserAccessDomainRepository<UserAccessDomain> useraccessdomainRepository;

    @Autowired
    private QuestionRepository<Question> questionRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("LoginPrimaryKey"));
            Login login = loginRepository.findById((java.lang.String) map.get("LoginPrimaryKey"));
            login.setFailedLoginAttempts(11);
            login.setVersionId(1);
            login.setLoginId("9j7aRkxbv6w5RB28ZYSYMFhrPrb5L2MYhkRVweUhnNr0lmBOmc");
            login.setServerAuthText("cUoxqVFNHCzkF779");
            login.setServerAuthImage("aWRSvlICxmVTM3TE4whk1ZzqinHqmdQ0");
            login.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            loginRepository.update(login);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("LoginPrimaryKey"));
            loginRepository.findById((java.lang.String) map.get("LoginPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void testNQFindUnMappedUser() {
        try {
            loginRepository.FindUnMappedUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testNQFindMappedUser() {
        try {
            loginRepository.FindMappedUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("LoginPrimaryKey"));
            loginRepository.delete((java.lang.String) map.get("LoginPrimaryKey")); /* Deleting refrenced data */
            questionRepository.delete((java.lang.String) map.get("QuestionPrimaryKey")); /* Deleting refrenced data */
            useraccessdomainRepository.delete((java.lang.String) map.get("UserAccessDomainPrimaryKey")); /* Deleting refrenced data */
            useraccesslevelRepository.delete((java.lang.String) map.get("UserAccessLevelPrimaryKey")); /* Deleting refrenced data */
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

    private void validateLogin(EntityTestCriteria contraints, Login login) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            login.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            login.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            login.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            loginRepository.save(login);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "loginId", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "loginId", "mEeK9szp8kB1vkn5jbGBSFEDXEWqdh94wy95eHyHnlJ0o0XPRh23nV9EFozuy9IORPd8iLuxJczg89k99QnmkjYC0le0g9QcDd2BcPexaumOhfwqixea242bYZAmRPIZ2n1EqHRz9qAP7S2SwppQtlUY45rXYBu92PlGK5ecZdpbNepLPJiYRKxycVplrf4xHA18k31L1"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "serverAuthImage", "a3YDZMYPacodRgYsQEmWp3phGDElqboMs"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "serverAuthText", "cOBvg4ggrpF2afkla"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "failedLoginAttempts", 18));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "isAuthenticated", true));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                Login login = createLogin(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = login.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(login, null);
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 2:
                        login.setLoginId(contraints.getNegativeValue().toString());
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 3:
                        login.setServerAuthImage(contraints.getNegativeValue().toString());
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 4:
                        login.setServerAuthText(contraints.getNegativeValue().toString());
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 5:
                        login.setFailedLoginAttempts(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 6:
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
