# BeautySalonServer
Server side of beauty salon application

Paths:
=====================Мастера============================
/masters [GET] - выводит всех мастеров в формате:

        "id": 1,
        "name": "Никифоров Денис",
        "tel": "80298403232",
        "percent": 0.02,
        "accounts": [
            {
                "id": 1,
                "username": "denis",
                "password": "denis200601",
                "admin": true,
                "masterId": 1
            }
        ],
        "orders": [
            {
                "id": 2,
                "price": 2000.0,
                "start": "2021-12-25T14:00:00.000+00:00",
                "finish": "2021-12-25T14:40:00.000+00:00",
                "masterId": 1,
                "clientId": 3
            }
        ]
 
        
/masters [POST] - добавляет нового мастера. В теле запроса надо указать данные по такому шаблону:

        "name": "Никифоров Денис",
        "tel": "80298403232",
        "percent": 0.02
     
/masters [PUT] - изменяет уже существующего мастера. В теле запроса надо указать id мастера и тот параметр, который надо изменить. Напимер:

{
    "id": 1,
    "name": "Some name"
}

/masters [DELETE] - удаляет мастера. Для удаления в теле запроса достаточно указать id мастера. Например:

{
    "id": 1
}

/masters/{id} [GET] - достает мастера с заданным id.

=====================Клиенты============================
/clients [GET] - выводит всех клиентов в формате:
{
        "id": 3,
        "name": "Вероника",
        "tel": "80298403232",
        "orders": [
            {
                "id": 2,
                "price": 2000.0,
                "start": "2021-12-25T14:00:00.000+00:00",
                "finish": "2021-12-25T14:40:00.000+00:00",
                "masterId": 1,
                "clientId": 3
            }
        ]
}
        
/clients [POST] - добавляет нового клиента. В теле запроса надо указать данные по такому шаблону:

        "name": "Вероника",
        "tel": "80298403232"
     
/clients [PUT] - изменяет уже существующего клиента. В теле запроса надо указать id клиента и тот параметр, который надо изменить. Напимер:

{
    "id": 3,
    "name": "Denis"
}

/clients [DELETE] - удаляет клиента. Для удаления в теле запроса достаточно указать id клиента. Например:

{
    "id": 3
}

/clients/{id} [GET] - достает клиента с заданным id.


=====================Заказы============================

/orders [GET] - выводит все заказы в формате:
{
        "id": 2,
        "price": 2000.0,
        "start": "2021-12-25T14:00:00.000+00:00",
        "finish": "2021-12-25T14:40:00.000+00:00",
        "masterId": 1,
        "clientId": 3
}
        
/orders [POST] - добавляет новый заказ. В теле запроса надо указать данные по такому шаблону:

        "price": 2000.0,
        "start": "2021-12-25T14:00:00.000+00:00",
        "finish": "2021-12-25T14:40:00.000+00:00",
        "masterId": 1,
        "clientId": 3
     
/orders [PUT] - изменяет уже существующий заказ. В теле запроса надо указать id заказа и тот параметр, который надо изменить. Напимер:

{
    "id": 2,
    "price": 230.50
}

/orders [DELETE] - удаляет заказ. Для удаления в теле запроса достаточно указать id заказа. Например:

{
    "id": 2
}

/orders/{id} [GET] - достает заказ с заданным id.
