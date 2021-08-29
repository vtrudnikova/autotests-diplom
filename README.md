
# Автотесты на страницу https://my.paragon-software.com/
### Cписок автоматизированных тест-кейсов в данном проекте:
- Невозможность логина незарегистрированным пользователем
- Невозможность логина c некорректным email
- На странице логина есть ссылка Sign in
- После изменения локализации текст переведен
- Можно вернуться к форме авторизации со страницы восстановления пароля
### Тест-кейсы в Allure TestOPS:
![Screenshot_3](https://user-images.githubusercontent.com/86876622/131252555-44ba0e15-2bfc-426f-87c1-31a071882c37.png)

### Запуск автотестов в Jenkins:
[Jobs для запуска](https://jenkins.autotests.cloud/job/start-project-lesson-12/)
### Параметры используемые при запуске тестов:
* browser (default chrome)
* browserVersion (default 89.0)
* browserSize (default 1920x1080)
* browserMobileView (mobile device name, for example iPhone X)
* remoteDriverUrl (url address from selenoid or grid)
* videoStorage (url address where you should get video)
* threads (number of threads)
### Запуск тестов в Jenkins:
- Открыть job 
- Нажать "Собрать с парметрами" - откроется окно где необходимо выбрать параметры для запуска

![Screenshot_6](https://user-images.githubusercontent.com/86876622/131253248-f38a2e8d-5858-4afd-91ff-1fcb904e3f2b.png)
- Нажать собрать

![Screenshot_8](https://user-images.githubusercontent.com/86876622/131253300-7229f3fa-c9f4-4721-a101-15a1b1d79540.png)

### Анализ результатов в Jenkins через AllureReports:
![Screenshot_10](https://user-images.githubusercontent.com/86876622/131253455-399dc8e9-2e9e-4a19-a357-e2208898f1dd.png)
![Screenshot_11](https://user-images.githubusercontent.com/86876622/131253465-840f3fe1-42ce-43d6-bd54-82225212e8f2.png)
![Screenshot_12](https://user-images.githubusercontent.com/86876622/131253495-d52ab347-d221-4355-82f4-3e1588abb4dd.png)
### Анализ результатов в Jenkins через Allure TestOPS:
![Screenshot_13](https://user-images.githubusercontent.com/86876622/131253540-0fd62509-30c7-45f6-a71b-9009ccf8d623.png)

### Запуск тестов в Allure TestOPS:
- Создать тест-план с автоматизированным набором тест-кейсов

![Screenshot_15](https://user-images.githubusercontent.com/86876622/131254008-0d299b53-c237-4c11-9c4b-0e11b734417e.png)
![Screenshot_16](https://user-images.githubusercontent.com/86876622/131254012-b016be31-b988-4e49-a2bd-e47aba18d44f.png)
- Нажать run - указать имя Launch - нажать Submit
- Перейти на вкладку Launches
![Screenshot_18](https://user-images.githubusercontent.com/86876622/131254116-bcc07462-4a7a-4c75-8991-aa5ca415272d.png)

Run tests with filled remote.properties:
```bash
gradle clean test
```

Run tests with not filled remote.properties:
```bash
gradle clean -DremoteDriverUrl=https://user1:1234@selenoid.autotests.cloud/wd/hub/ -DvideoStorage=https://selenoid.autotests.cloud/video/ -Dthreads=1 test
```

Serve report:
```bash
allure serve build/allure-results
```


### После прохождения тестов в telegram канал приходит оповещение с результатом прогона:
![Screenshot_14](https://user-images.githubusercontent.com/86876622/131253867-f2a9e365-bcfa-44ce-9985-0ee45d3d1478.png)

[Telegram channel](https://t.me/auto_tests_paragon)


### Запись прохождения тестов:
