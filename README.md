# PADSOF — Centro de exposiciones

Aplicación de escritorio para gestionar un centro de exposiciones. Permite
consultar exposiciones y ofrece flujos diferenciados para clientes registrados,
empleados y el gestor. Está desarrollada en Java con Swing y organiza las vistas
y controladores siguiendo un enfoque MVC. Desarrollada para la asignatura de PADSOF
de la UAM.

## Requisitos

- Java JDK 11 o posterior (`java` y `javac`).
- GNU Make.
- Ejecutar los comandos desde la raíz del proyecto. La aplicación usa rutas
  relativas para sus datos, imágenes, recursos y tickets.

## Compilar y ejecutar

```bash
make help
make build
make run
```

`make run` compila y abre la interfaz gráfica. Los objetivos disponibles son:

| Comando | Acción |
| --- | --- |
| `make help` | Muestra los objetivos disponibles. |
| `make build` | Compila las fuentes Java en `build/classes/`. |
| `make run` | Compila y ejecuta `Interfaz.VentanaPrincipal`. |
| `make seed` | Compila y genera de nuevo los datos de ejemplo. |
| `make clean` | Elimina las clases compiladas de `build/`. |

Las dependencias Java necesarias para compilar y ejecutar están en
`resources/`: JDatePicker, el simulador de pagos y la biblioteca de tickets.

## Librerías externas

Además de Java Swing, que forma parte del JDK, el proyecto utiliza librerías
externas distribuidas en `resources/`:

- `jdatepicker-1.3.4.jar`: componentes de selección de fechas.
- `TeleChargeAndPaySystem8.jar`: simulación de validación y cobro de tarjetas.
- `TicketSystem.jar`: generación de tickets en PDF.

El `Makefile` incorpora estas dependencias al classpath durante la compilación
y la ejecución. No es necesario descargarlas por separado si los JAR están
presentes en `resources/`.

## Datos de ejemplo y persistencia

La aplicación carga y guarda el estado serializado en
[`sistema_rellenado.dat`](./sistema_rellenado.dat). Al cerrar la ventana se
guardan los cambios en ese archivo.

Para regenerar el conjunto de ejemplo:

```bash
make seed
```

El generador `Demostrador.GenerarSistema` sustituye el contenido de
`sistema_rellenado.dat` por los datos de muestra y comprueba que se pueden volver
a cargar. **Este comando reemplaza los datos actuales de ese archivo.** Haz una
copia si quieres conservarlos. La aplicación actual no usa `sistema.dat`; el
manual anterior lo mencionaba, pero el fichero de persistencia configurado es
`sistema_rellenado.dat`.

El conjunto de ejemplo incluye:

- 12 clientes y 6 empleados con permisos variados, además del gestor.
- 36 obras entre pinturas, esculturas, fotografías y audiovisuales; incluye
  estados de almacén, préstamo, restauración y retirada.
- 14 salas, climatizadas y no climatizadas, incluida una sala dividida.
- 14 exposiciones permanentes y temporales con distintos estados, fechas,
  salas, obras, imágenes y descuentos.
- 6 sorteos con los tres tipos disponibles, inscripciones activas y códigos ya
  generados.
- Entradas compradas y ventas de mostrador, junto con notificaciones de muestra.

El generador asigna imágenes de `imgs/` a las exposiciones. Para que se vean,
los archivos deben seguir siendo imágenes válidas y estar en esa carpeta.

## Primer acceso

Selecciona el tipo de usuario en el formulario de inicio de sesión. En clientes
y empleados se introduce el DNI como identificador; el gestor usa nombre de
usuario.

| Perfil | Identificador | Contraseña |
| --- | --- | --- |
| Gestor | `JefeAlberto` | `Password123` |
| Empleado de ejemplo con permisos amplios | `123456789F` | `empleado2024` |
| Cliente de ejemplo | `00000001A` | `holamundo` |
| Otro cliente de ejemplo | `20000000B` | `malakito23` |

Todos los empleados usan la contraseña común `empleado2024`; sus permisos
dependen de cada registro. Los clientes adicionales usan identificadores
`40000003D` en adelante y contraseñas como `cliente42026`, incrementando el
número según su orden en la lista de ejemplo.

## Funcionalidades principales

### Invitado

- Consultar las exposiciones disponibles y ver sus detalles.
- Registrarse como cliente y acceder al inicio de sesión.

#### Guía: consultar exposiciones como invitado

1. Ejecuta `make run`.
2. En la pantalla inicial, abre el listado de exposiciones.
3. Usa los filtros disponibles para limitar el listado por tipo o fechas.
4. Pulsa el botón de ver detalles de una exposición para consultar sus obras,
   salas, fechas, descripción e imagen.

#### Guía: registrarse como cliente

1. En la pantalla de inicio, selecciona el acceso de registro.
2. Introduce el DNI/NIF y la contraseña solicitados.
3. Indica si quieres recibir notificaciones.
4. Confirma el registro. Después, selecciona el perfil de cliente en el inicio
   de sesión e identifícate con tu DNI/NIF y contraseña.

### Cliente registrado

- Consultar y filtrar exposiciones.
- Comprar entradas para una exposición y consultar el precio con descuentos.
- Participar en sorteos disponibles y revisar notificaciones.
- Usar códigos de sorteo cuando se le haya asignado uno.

#### Guía: consultar y filtrar exposiciones

1. Inicia sesión como cliente y abre la pestaña **Exposiciones**.
2. Selecciona los criterios de filtrado disponibles en el panel lateral.
3. Consulta el listado resultante y pulsa el botón de detalles para abrir una
   exposición.
4. Usa **Atrás** para regresar al listado.

#### Guía: comprar entradas

1. En **Exposiciones**, selecciona una exposición disponible y pulsa el botón
   de compra.
2. Elige el número de entradas, la fecha y una hora de visita válida.
3. Revisa el precio mostrado, que puede incluir descuentos aplicables.
4. Deja vacío el campo de código si no vas a usar uno, o introduce el código
   recibido en una notificación.
5. Introduce una tarjeta de prueba de 16 dígitos, por ejemplo
   `1234567890123456`, y confirma la compra.
6. Al completarse, la aplicación genera el ticket PDF en `tmp/`.

Los códigos de sorteo tienen condiciones de uso: pueden requerir una fecha y
hora concretas o una visita dentro del periodo indicado. Un código válido solo
se puede utilizar para la cantidad de entradas que permita el sorteo.

#### Guía: participar en sorteos y consultar notificaciones

1. Abre la pestaña **Sorteos** para consultar los sorteos activos.
2. Selecciona uno y revisa sus fechas de inscripción, tipo y condiciones de
   visita.
3. Indica si solicitas una o dos entradas y confirma la inscripción.
4. Abre **Notificaciones** para revisar los avisos y posibles códigos
   asignados.
5. Si recibes un código, úsalo al comprar una entrada que cumpla las condiciones
   del sorteo.

### Empleado

- Consultar exposiciones y vender entradas presenciales si su perfil tiene ese
  permiso.
- Modificar las condiciones de climatización de salas si tiene autorización.
- Enviar notificaciones si tiene permiso.
- Consultar y modificar algunos datos de su perfil.

#### Guía: vender entradas en mostrador

1. Inicia sesión con un empleado que tenga permiso de venta.
2. En **Exposiciones**, selecciona la exposición y pulsa el botón de venta.
3. Elige cantidad y hora para la visita del día actual.
4. Introduce la tarjeta de prueba y confirma la operación.

Si el empleado no tiene permiso para vender entradas, la aplicación bloquea el
flujo y muestra un aviso.

#### Guía: cambiar la climatización de una sala

1. Inicia sesión con un empleado que tenga permiso para cambiar la climatización.
2. Abre **Modificar salas** y selecciona una sala climatizada.
3. Pulsa **Modificar** para mostrar las opciones de la sala.
4. Introduce los nuevos rangos de temperatura y humedad y confirma.
5. Si hay obras expuestas, los rangos deben respetar sus condiciones de
   conservación.

#### Guía: enviar notificaciones y editar el perfil

1. Para enviar un aviso, abre **Enviar notificación**, escribe el mensaje y
   pulsa el botón de envío. Se requiere el permiso correspondiente.
2. Para revisar o cambiar los datos personales, abre **Editar datos**, modifica
   los campos habilitados y guarda los cambios.

### Gestor

- Crear, editar y eliminar exposiciones; programar sorteos y gestionar
  descuentos.
- Gestionar obras, incluidas su importación desde CSV, almacenamiento,
  exposición, préstamo y restauración.
- Crear y modificar salas, incluido dividir salas compatibles.
- Registrar empleados, administrar el horario, consultar estadísticas y revisar
  notificaciones.

#### Guía: crear y editar exposiciones

1. Inicia sesión como gestor y abre **Exposiciones**.
2. Selecciona la acción para programar una exposición.
3. Completa nombre, autor, descripción, precio y tipo; si es temporal, indica
   las fechas de inicio y fin.
4. Pulsa **Examinar archivo...** y elige una imagen. El generador de ejemplo
   usa imágenes guardadas en `imgs/`.
5. Selecciona una sala y pulsa **Crear exposición**.
6. Para modificarla, vuelve a **Exposiciones**, selecciona la exposición y
   utiliza **Editar** para cambiar el precio o sus salas.
7. Usa **Cambiar de fase** cuando quieras publicar una exposición en creación.
   Las acciones de interrupción, prórroga y cancelación dependen del tipo y
   estado de la exposición.
8. Para borrar una exposición, selecciónala y pulsa **Borrar**. Solo se permite
   borrar si está en creación, cancelada o terminada; confirma la operación.

#### Guía: gestionar descuentos y sorteos

1. En **Exposiciones**, selecciona la exposición a configurar.
2. Abre **Descuentos** y selecciona **Añadir**.
3. Escoge descuento **Por compra** o **Por antelación**, introduce el porcentaje
   y el umbral solicitado, y confirma.
4. Para crear un sorteo, selecciona **Sorteo** y configura el número de entradas,
   el periodo de inscripción y el tipo de sorteo.
5. Completa las fechas y horas adicionales que exija el tipo elegido y guarda
   el sorteo.

#### Guía: gestionar obras

1. Abre la pestaña **Obras**.
2. Para añadir obras desde un fichero, selecciona la opción de importación y
   elige un CSV compatible; `resources/obras.csv` contiene datos de ejemplo.
3. Para crear una obra manualmente, selecciona el tipo y completa sus datos,
   dimensiones y condiciones de conservación si las requiere.
4. Selecciona una obra para consultar sus detalles.
5. Usa las acciones disponibles para exponerla en una sala compatible,
   almacenarla, prestarla, retirarla o iniciar su restauración, respetando el
   estado actual de la obra.

#### Guía: gestionar salas

1. Abre **Modificar salas** en el perfil de gestor.
2. Selecciona la acción para crear una sala y elige si será climatizada.
3. Completa nombre, aforo, tomas de corriente y dimensiones; para salas
   climatizadas, introduce también rangos de temperatura y humedad.
4. Para dividir una sala, selecciónala y proporciona los datos de las dos
   subsalas. Las dimensiones, el aforo y las tomas deben ser compatibles con la
   sala original, que no puede estar asociada a una exposición.

#### Guía: registrar empleados, cambiar horario y consultar estadísticas

1. En **Registrar empleado**, introduce DNI/NIF, NSS, dirección y número de
   cuenta.
2. Marca los permisos que tendrá el empleado y pulsa **Registrar empleado**.
   Los empleados registrados usan la contraseña común indicada en este README.
3. Abre **Horario**, selecciona las horas de apertura y cierre y confirma el
   cambio.
4. En **Exposiciones**, selecciona una exposición y abre **Estadísticas** para
   consultar sus datos; indica un intervalo de fechas si quieres limitar el
   informe.
5. Para estadísticas globales, usa el botón de estadísticas globales del
   listado de exposiciones.
6. Abre **Notificaciones** para consultar los avisos del gestor.

## Probar una compra

1. Ejecuta `make run` y accede como cliente con `00000001A` / `holamundo`.
2. Abre **Exposiciones** y selecciona una exposición disponible para comprar.
3. Escoge el número de entradas, fecha y hora; deja vacío el campo de código si
   no estás usando un código de sorteo.
4. Introduce un número de tarjeta de prueba de 16 dígitos, por ejemplo
   `1234567890123456`, y confirma la compra.

Los pagos se gestionan mediante la biblioteca simulada incluida; no se realiza
un cargo bancario real. En la compra correcta, el ticket se guarda como PDF en
`tmp/`, relativo al directorio desde el que se lanzó la aplicación.

## Estructura del proyecto

```text
src/
  Demostrador/   Generador de datos de ejemplo y demostración
  Entrada/       Entradas compradas y no compradas
  Exposicion/    Exposiciones, estados y descuentos
  Horario/       Horario del centro
  Interfaz/      Ventana Swing, paneles, controladores y utilidades visuales
  Obra/          Pinturas, esculturas, fotografías y audiovisuales
  Sala/          Salas climatizadas y no climatizadas
  Sistema/       Modelo central, autenticación y persistencia
  Sorteo/        Sorteos, participantes y códigos
  Ticket/        Adaptador a la biblioteca de tickets
  Usuario/       Gestor, empleados, clientes y notificaciones
  Utils/         Dimensiones, temperatura, humedad y estados comunes
resources/       Bibliotecas, imágenes auxiliares y CSV de obras
imgs/            Imágenes de exposiciones
tmp/             Tickets PDF generados
docs/
  diagrams/      Diagramas del proyecto
  javadoc/       Referencia HTML de las clases y métodos
  archive/       Material archivado
build/classes/   Salida generada por `make build`
```

La interfaz arranca en `Interfaz.VentanaPrincipal`. Los paquetes de dominio
contienen el modelo; `Interfaz/Paneles` define las vistas y
`Interfaz/Controladores` conecta las acciones de la interfaz con la lógica del
sistema.

## Datos y documentos auxiliares

- `resources/obras.csv`: catálogo de ejemplo para importar obras.
- `resources/testobras.csv`: CSV pequeño de prueba.
- `docs/javadoc/index.html`: documentación técnica generada de la API.
- `docs/diagrams/`: diagrama de clases del proyecto.

No hay una suite de pruebas automatizadas configurada en el proyecto; `make
build` verifica que las fuentes compilan.
