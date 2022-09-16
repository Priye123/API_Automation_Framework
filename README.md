# TestVagrant-Technologies:- Code Assessment

A basic API Automation Framework to validate JSON data of RCB for upcoming match.
#### Table of Contents
1.  [Description](#description)
2.  [IDE,Tools & Technologies used](#installation)
3.  [Folder Structure](#usage)
4.  [Steps](#links)
5.  [Code of conduct](#contribute)
8.  [Conclusion](#license)

## <a id="description"></a>Description

This Repo contains Test automation Scripts to validate below scenarios:
* To validate the RCB team has only 4 foreign players
* To validate the team has at least 1 wicket keeper

## <a id="installation"></a>IDE,Tools & Technologies used

```bash
Eclipse,Java,TestNG,Maven,Log4J,ExtentReports,SMTP Mail,GIT
```

## <a id="usage"></a>Folder Structure

![image](https://user-images.githubusercontent.com/46869321/190618683-6ed221d1-2295-4333-a075-b3274f798932.png)

## <a id="links"></a>Steps

*   First, we will take JSON from [TeamRCB.json](https://gist.github.com/kumarpani/1e759f27ae302be92ad51ec09955e765)
*   Fetch JSON file from specified file location
*   2 testcases written with assertion statements to validate players inside try-catch block to catch customized exception
*   Done dynamic Reporting with ExtentReports.All logs are reflected in reports also
*   Used LoggerFactory class for print data into console.
*   Testcases triggered from a single Regression_Player_Validation.xml
*   Attached Title & TestVagrant Technologies logo in reports by creating ReportConfig.xml file & link with existing reports.company logo taken from [TestVagrant Technologies logo](https://media-exp1.licdn.com/dms/image/C4E0BAQHbAbSCBOYGIQ/company-logo_200_200/0/1520937439451?e=2159024400&v=beta&t=BDZxgYSM2UtT46BK1Oglh0WEGC6dT3-iJdK0f8dB4Rs)

![image](https://user-images.githubusercontent.com/46869321/190624843-2085d1a2-66b6-4e51-b065-15545bc82540.png)

*   After all testcases run,at last It will send you the mail with attached reports to various recipients.It is configured in Mailing.java and it is used in      @AfterSuite in BaseClass

![image](https://user-images.githubusercontent.com/46869321/190625297-7883a865-258b-4da9-bc72-7837cd05b213.png)


## <a id="license"></a>Conclusion

This is the final submission from my side.\
Thanks & Regards,\
Priya Ranjan
