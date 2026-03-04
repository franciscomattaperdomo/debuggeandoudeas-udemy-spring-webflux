# Instrucciones para habilitar el proyecto Gradle en IntelliJ IDEA

## Problema Resuelto

He configurado los archivos necesarios para que IntelliJ IDEA reconozca este proyecto como un proyecto Gradle. Los siguientes cambios se realizaron:

### 1. Archivos de configuración creados en `.idea/`:
- `gradle.xml` - Configuración de Gradle para IntelliJ
- `misc.xml` - Configuración del JDK (Java 17)
- `modules.xml` - Configuración de módulos del proyecto
- `compiler.xml` - Actualizado con configuración de procesadores de anotaciones (Lombok + MapStruct)

### 2. Cambio en `build.gradle`:
- Se cambió la versión de Java de 21 a 17 para coincidir con tu JDK instalado

### 3. Verificación:
- ✅ El proyecto compila correctamente con `./gradlew clean build`
- ✅ Todas las dependencias se resuelven correctamente
- ✅ Los tests pasan exitosamente

## Pasos para refrescar el proyecto en IntelliJ IDEA:

### Opción 1: Reimportar Gradle (Recomendado)
1. En IntelliJ IDEA, haz clic en el panel **Gradle** en el lado derecho
2. Haz clic en el ícono de **Reload All Gradle Projects** (🔄)
3. Espera a que IntelliJ sincronice el proyecto

### Opción 2: Desde el menú File
1. Ve a **File → Invalidate Caches / Restart**
2. Selecciona **Invalidate and Restart**
3. Espera a que IntelliJ reinicie y reindexe el proyecto

### Opción 3: Abrir como proyecto Gradle
1. Cierra el proyecto actual en IntelliJ IDEA
2. Ve a **File → Open**
3. Navega a la carpeta: `/home/fcmm/springboot/debuggeandoudeas-udemy-spring-webflux/eats_hub_catalog`
4. Selecciona el archivo **build.gradle**
5. Cuando se te pregunte, selecciona **Open as Project**
6. En el diálogo de importación, asegúrate de seleccionar:
   - **Use Gradle from**: 'gradle-wrapper.properties' file
   - **Gradle JVM**: Java 17

## Verificación del proyecto

Para verificar que todo funciona correctamente desde la terminal:

```bash
cd /home/fcmm/springboot/debuggeandoudeas-udemy-spring-webflux/eats_hub_catalog
./gradlew clean build
```

Si el build es exitoso, el proyecto está correctamente configurado.

## Tecnologías del proyecto

- **Spring Boot**: 3.4.5
- **Java**: 17
- **Gradle**: 8.13
- **Spring WebFlux**: Programación reactiva
- **MongoDB Reactive**: Base de datos NoSQL con Spring Data Reactive
- **Lombok**: Reducción de código boilerplate
- **MapStruct**: Mapeo de objetos

## Ejecutar la aplicación

```bash
./gradlew bootRun
```

O desde IntelliJ IDEA, ejecuta la clase principal: `EatsHubCatalogApplication`

