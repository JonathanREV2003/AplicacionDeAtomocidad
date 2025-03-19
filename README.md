<<<<<<<<<<< APLICACION APLICACION DE ATOMICIDAD >>>>>>>>>>>>

Tarea: 

Utilizando el ejemplo de la base de datos de hipervet utilice la tabla cita yla tabla detallecita y construya un web api que reciba como parámetros los valores de la tabla Cita,
y el conjunto de las filas que pudiera tener la tabla detallecita.
En una sola transacción implmentada en un mismo procedimiento almacenado debe insertarse los valores de la tabla cita y el conjunto de valores de la tabla detallecita.
Este procedimiento almacenado debe ser invocado por la web api.

-----------------------------------------------------------------------------------------------------------------------------------------------------------------------

1. para el manejo de este trabajo identificamos primero las tablas que vamos a ocupar y en base a ellas creamos el siguiente procedimiento almacenado en -----SQL Management Studio-----:
   
![image](https://github.com/user-attachments/assets/d84cc1db-cb9c-43f7-b269-52625741c099)

- Explicación: 
Los valores que recibimos de la api ret son en formato Json (un arreglo de datos), en base a estos datos creamos una tabla temporal que almacena todos los datos del json,
para posteriormente colocar los datos corespondientes en sus tablas desde la tabla temporal.

-----------------------------------------------------------------------------------------------------------------------------------------------------------------------

2. La api rest fue desarrollada con SpringBot con su respectivos entities, Repositories, DAO, <Controlers>, esto nos ayuda al manejo de la introduccion de datos por medio de
un array de datos tipo json.

-----------------------------------------------------------------------------------------------------------------------------------------------------------------------

un ejemplo de los datos introduciodos en formato json es: 

![image](https://github.com/user-attachments/assets/87cbc0a6-e055-453c-8b34-be59d4f0aa82)

Tablas donde se insertaron los datos: DBO[Cita]
![image](https://github.com/user-attachments/assets/8c40576d-468c-406c-b300-50773911d01c)

DBO[CitaDetalle]

![image](https://github.com/user-attachments/assets/51499b1c-eea1-4c5e-b48e-653fdf18dcf3)
