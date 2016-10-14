# TryingAbilitiesOfCapabilities



Тест: Изменение параметров браузера ("user agent") и их проверка на сайте "http://www.whatsmyua.com",
отображающем текущие  настройки браузера ("user agent"). 

Для запуска теста можно указать желаемый тип браузера, путь и имя файла драйвера 
в файле testng.xml.  В настоящий момент можно использовать Opera и Chrome. Данные о 
типе браузера, путь и имя файла драйвера будут взяты тестом через @Parameters из файла 
testng.xml, и их можно менять. Примеры настройки приведены в данном файле (см. ниже) 
и в файле testng.xml. Данные для установки нового "user agent" тестовые методы получают
через @Dataprovider.

Тест считается прйденным, если на главной веб странице "http://www.whatsmyua.com" найдено 
одно из слов: "Android", "BlackBerry" или "SunOS"; 

ЗАПУСК И НАСТРОЙКА ТЕСТА

I. СБОРКА ТЕСТА: НАСТРОЙКА POM.XML

1.1 ЗАВИСИМОСТИ
1.2 ПЛАГИНЫ

II. ЗАПУСК ТЕСТА: НАСТРОЙКА TESTNG.XML

2.1 ТИП БРАУЗЕРА В TESTING.XML
2.2 ПУТЬ И ИМЯ ФАЙЛА ДРАЙВЕРА В ФАЙЛЕ TESTING.XML
2.3 ПРИМЕРЫ

ЗАПУСК И НАСТРОЙКА ТЕСТА

I. СБОРКА ТЕСТА: НАСТРОЙКА POM.XML
1.1 ЗАВИСИМОСТИ (dependencies)

Следующие описания зависимостей должны присутствовать в файле POM.XML для сборки теста. Они добавят новые или 
перепишут зависимости используемые по умолчанию. Для справки см. в IntelliJ Idea: Maven -> Maven Effective POM.

```xml
<!-- https://mvnrepository.com/artifact/org.testng/testng -->
   <dependency>
        <groupId>org.testng</groupId>
        <artifactId>testng</artifactId>
        <version>6.8</version>
        <scope>test</scope>
    </dependency>


<!-- https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java -->
<dependency>
    <groupId>org.seleniumhq.selenium</groupId>
    <artifactId>selenium-java</artifactId>
    <version>3.0.0-beta4</version>
</dependency>

<!-- https://mvnrepository.com/artifact/org.apache.commons/commons-lang3 -->
<dependency>
    <groupId>org.apache.commons</groupId>
    <artifactId>commons-lang3</artifactId>
    <version>3.4</version>
</dependency>
```

1.2 ПЛАГИНЫ

Следующие настройки должны присутствовать в файле POM.XML для сборки и запуска теста. Будут добавлены
новые настройки или переписаны существующие по умолчанию. Используется JDK 1.8. 
Для справки см. в IntelliJ Idea: Maven -> Maven Effective POM

```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-surefire-plugin</artifactId>
    <version>2.10</version>
    <configuration>
        <suiteXmlFiles>
           <suiteXmlFile>src/test/resources/testng.xml</suiteXmlFile>
        </suiteXmlFiles>
    </configuration>
</plugin>
```
II. ЗАПУСК ТЕСТА: НАСТРОЙКА TESTNG.XML

2.1 ТИП БРАУЗЕРА В TESTNG.XML

Для запуска теста можно указать желаемый тип браузера в testng.xml По умолчанию будет использовано значение "chrome".

2.2 ПУТЬ И ИМЯ ФАЙЛА ДРАЙВЕРА В ФАЙЛЕ TESTNG.XML

Если указан желаемый тип браузера, то небходимо указать путь и имя файла драйвера в testng.xml По умолчанию будет 
использовано значение: "D:\PersonalDrivers\chromedriver.exe"

2.3 ПРИМЕРЫ 

Примеры также можно посмотреть в файле testng.xml

```xml
            <parameter name="browser"  value="chrome"/>
            <parameter name="pathToDriver"  value="D:\PersonalDrivers\chromedriver.exe"/>

            <parameter name="browser"  value="opera"/>
            <parameter name="pathToDriver"  value="D:\PersonalDrivers\operadriver.exe"/>
```
