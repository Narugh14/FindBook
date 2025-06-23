# 📚 FindBook - Buscador de Libros en CLI

[![Java Version](https://img.shields.io/badge/Java-17%2B-orange?logo=openjdk)](https://openjdk.org/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.1%2B-brightgreen?logo=spring)](https://spring.io/projects/spring-boot)
[![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg)](https://opensource.org/licenses/MIT)

Herramienta de línea de comandos (CLI) para buscar información de libros usando Spring Boot y Java 17+. Consulta datos bibliográficos directamente desde tu terminal.

## ✨ Características

- Búsqueda de libros por título, autor o ISBN
- Resultados en formato tabla legible
- Exportación a JSON/CSV (opcional)
- Integración con APIs de libros (Ej: Google Books, Open Library)
- Configuración personalizable

## ⚙️ Prerrequisitos

- Java 17 o superior
- Maven 3.6+
- (Opcional) Clave de API para servicios externos

## 🚀 Instalación

1. Clona el repositorio:

```bash
git clone https://github.com/Narugh14/FindBook.git
```
Construye la aplicación:
```
bash
cd FindBook
mvn clean package
```
## 🖥️ Uso
Ejecuta la aplicación con el comando:
```
bash
java -jar target/findbook-*.jar [PARÁMETROS]
```
Ejemplos de comandos:
Buscar por título:
```
bash
java -jar target/findbook-*.jar search --title "El señor de los anillos"
```
Buscar por autor:
```
bash
java -jar target/findbook-*.jar search --author "Gabriel García Márquez"
```
Mostrar ayuda:
```
bash
java -jar target/findbook-*.jar --help
```
Parámetros principales:
Opción	Descripción
```
--title	Buscar por título
--author	Buscar por autor
--isbn	Buscar por ISBN
--output	Formato de salida (table/json/csv)
--save	Guardar resultados en archivo
```
## 🛠️ Configuración
Crea un archivo application.yml para personalizar:
```
yaml
findbook:
  api:
    provider: google-books # google-books | open-library
    key: tu-api-key
  results:
    default-format: table
    max-results: 5
```
## 🧪 Ejecución en IDE
Ejecuta la clase principal: com.narugh.findbook.FindBookApplication con argumentos:

text
--title "Cien años de soledad"

## 🌳 Estructura del Proyecto
```
text
src/
├── main/
│   ├── java/
│   │   └── com/narugh/findbook/
│   │       ├── cli/          # Lógica de comandos
│   │       ├── api/           # Integración con APIs
│   │       ├── models/        # POJOs de libros
│   │       ├── services/      # Lógica de negocio
│   │       └── FindBookApplication.java
│   └── resources/
│       └── application.yml    # Configuración
└── test/                      # Pruebas unitarias
```
## 🤝 Cómo Contribuir
Haz fork del proyecto

Crea una rama (git checkout -b feature/nueva-funcionalidad)

Haz commit de tus cambios (git commit -m 'Add some feature')

Haz push a la rama (git push origin feature/nueva-funcionalidad)

Abre un Pull Request

## 📄 Licencia
Distribuido bajo la licencia MIT. Ver LICENSE para más detalles.
