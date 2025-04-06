#!/bin/bash

# Configuración de las carpetas
SRC_DIR="src"
BIN_DIR="bin"
PROGRAM_NAME="com.syncronizarworkspace.init.Main"
PROGRAM_PATH="${PROGRAM_NAME//./\/}.java"

# Verificar la existencia del archivo Main.java
if [ ! -f "$SRC_DIR/$PROGRAM_PATH" ]; then
    echo "Error: no se encontró el archivo $PROGRAM_PATH en $SRC_DIR"
    exit 1
fi

# Crear el directorio bin si no existe
if [ ! -d "$BIN_DIR" ]; then
    mkdir -p "$BIN_DIR"
    echo "Directorio '$BIN_DIR' creado."
fi

# Compilar los archivos .java
echo "Compilando archivos Java desde '$SRC_DIR' a '$BIN_DIR'..."
javac -d "$BIN_DIR" $(find "$SRC_DIR" -name "*.java")

# Verificar si la compilación fue exitosa
if [ $? -eq 0 ]; then
    echo "Compilación exitosa. Archivos .class generados en '$BIN_DIR'."
else
    echo "Error durante la compilación."
    exit 1
fi

# Ejecutar el programa
echo "Ejecutando '$PROGRAM_NAME' desde '$BIN_DIR'..."
echo "***************************************************"
echo "***************************************************"
echo "                                                   "
java -Dawt.useSystemAAFontSettings=on -Dswing.aatext=true -Dsun.java2d.uiScale=1 -cp "$BIN_DIR" "$PROGRAM_NAME"
echo "                                                   "
echo "***************************************************"
echo "***************************************************"
echo "                                                   "

# Verificar si la ejecución fue exitosa
if [ $? -eq 0 ]; then
    echo "Ejecución completada con éxito."
else
    echo "Error durante la ejecución."
    exit 1
fi
