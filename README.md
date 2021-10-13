
# Автотесты на страницу https://my.paragon-software.com/
### Cписок автоматизированных тест-кейсов в данном проекте:
UI:
- Невозможность логина незарегистрированным пользователем
- Невозможность логина c некорректным email
- На странице логина есть ссылка Sign in
- После изменения локализации текст переведен
- Можно вернуться к форме авторизации со страницы восстановления пароля

API:
- Нельзя зарегистрировать невалидный серийный номер
- Проверить, что есть kind с типом Technical issue и productConstraints с типом Free для new request
- У нового пользователя нет зарегистрированных продуктов
### Тест-кейсы в Allure TestOPS:
![Screenshot_7](https://user-images.githubusercontent.com/86876622/137185778-da70b464-6847-4a1f-8e77-cc194bfda51f.png)


### Запуск автотестов в Jenkins:
[Jobs для запуска](https://jenkins.autotests.cloud/job/auto-tests-paragon/)
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

![Screenshot_8](https://user-images.githubusercontent.com/86876622/137186014-43dd3957-9dc4-493c-a8b8-f126f3b8ba68.png)

- Нажать собрать

![Screenshot_9](https://user-images.githubusercontent.com/86876622/137186208-2e6fd3ab-a191-4141-b93f-43c40ad22f6d.png)
![Screenshot_11](https://user-images.githubusercontent.com/86876622/137186646-a83321d6-c2de-427e-8848-6b4c91e1ac22.png)


### Анализ результатов в Jenkins через AllureReports:
![Screenshot_11](https://user-images.githubusercontent.com/86876622/137186628-d32dce12-341c-4c7c-9c82-b7235e803905.png)
![Screenshot_12](https://user-images.githubusercontent.com/86876622/137186641-7a11cc47-fd80-4b0e-b08f-78e975a541ce.png)
![Screenshot_13](https://user-images.githubusercontent.com/86876622/137186644-c7c1eaec-07b3-44bb-8bb6-9b102024fb4c.png)

### Анализ результатов в Jenkins через Allure TestOPS:
![Screenshot_13](https://user-images.githubusercontent.com/86876622/137186846-9a26e080-407e-4903-85de-53c3dcea7f57.png)
![Screenshot_15](https://user-images.githubusercontent.com/86876622/137186851-4f963b05-24a3-4441-8c8e-ec8bd157680c.png)

### Запуск тестов в Allure TestOPS:
- Создать тест-план с автоматизированным набором тест-кейсов
![Screenshot_15](https://user-images.githubusercontent.com/86876622/137187275-ded62b4e-92fd-4696-9083-23ed16737117.png)


- Нажать run - указать имя Launch - нажать Submit
- Перейти на вкладку Launches

![Screenshot_16](https://user-images.githubusercontent.com/86876622/137187297-4d85f876-bd61-41e9-b081-01efb0a34d7a.png)

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
![Screenshot_18](https://user-images.githubusercontent.com/86876622/137187379-5b7e61ca-e680-4516-a0d5-e4ccccd0e6f4.png)

[Telegram channel](https://t.me/auto_paragon_tests)


### Запись прохождения тестов:

![merge_from_ofoct](https://user-images.githubusercontent.com/86876622/131255581-5e0b1ef5-e3f0-4aff-b93d-9807bf8f590d.gif)
