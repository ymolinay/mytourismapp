Reto Técnico - App Turística

1. Splash, vista que consume datos desde FirebaseFireStore y valida que las versión mínima del app sea la 2.0.5. Si la versión es inferior, mostrará una Dialog de forzado de actualización.
2. Lista, vista que consume un servicio desde un ApiRest (MockServer) y muestra una lista de items. Permite además ir al detalle de cualquiera de estos items.
3. Detalle, vista que consume un servicio desde un ApiRest (MockServer) y muestra el detalle de un item seleccionado desde la lista. Muestra además un albúm de fotos y un mapa con la ubicación del lugar.

Tecnologías usadas:

Retrofit: para consumir un ApiRest MockServer
Firebase Firestore: para obtener datos de configuración del app
Dagger Hilt: inyección de depedencias.
OsmDroid: para el uso de un mapa simple de simple configuración.
MVVM, Clean Arquitecture, clean code, solid
Version Catalog, para la organización de todas las dependencias.

Se han creado 3 flavor en una sola dimensión: Dev, Qa, Pro

Se adjunta el archivo postman del MockServer usado para el reto.



