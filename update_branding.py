import os

# Configuración
DIRECTORY = '.' 
EXTENSIONS = ('.kt', '.kts', '.java')

# Tu nueva firma
NUEVA_FIRMA = "/* #AppInitDev -> Photo Utility Hub */\n\n"

# El bloque exacto que queremos eliminar
# Usamos triple comilla para capturar exactamente lo que pegaste
BLOQUE_A_BORRAR = """/*
 * ImageToolbox is an image editor for android
 * Copyright (c) 2026 T8RIN (Malik Mukhametzyanov)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * You should have received a copy of the Apache License
 * along with this program.  If not, see <http://www.apache.org/licenses/LICENSE-2.0>.
 */"""

def procesar():
    count = 0
    # Normalizamos el bloque para que no importen los saltos de línea \r\n o \n
    bloque_limpio = "\n".join([line.rstrip() for line in BLOQUE_A_BORRAR.splitlines()])

    for root, _, files in os.walk(DIRECTORY):
        if 'build' in root or '.git' in root: continue
        
        for file in files:
            if file.endswith(EXTENSIONS):
                file_path = os.path.join(root, file)
                
                try:
                    with open(file_path, 'r', encoding='utf-8', errors='ignore') as f:
                        content = f.read()
                    
                    # Normalizamos el contenido del archivo temporalmente para comparar
                    content_normalized = "\n".join([line.rstrip() for line in content.splitlines()])
                    
                    if bloque_limpio in content_normalized:
                        # Reemplazamos el bloque exacto por tu firma
                        # Usamos replace en el contenido normalizado para asegurar el cambio
                        new_content = content_normalized.replace(bloque_limpio, NUEVA_FIRMA).lstrip()
                        
                        with open(file_path, 'w', encoding='utf-8') as f:
                            f.write(new_content)
                        
                        print(f"✅ ¡ÉXITO! Limpiado: {file_path}")
                        count += 1
                except Exception as e:
                    print(f"❌ Error en {file_path}: {e}")

    print(f"\n--- Finalizado ---")
    print(f"Archivos actualizados: {count}")

if __name__ == "__main__":
    procesar()