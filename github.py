import os
import random
from datetime import datetime, timedelta

# 1. Configuración de fechas
start_date = datetime(2025, 5, 6)
end_date = datetime.now()
days_to_run = (end_date - start_date).days + 1

# 2. Configuración de realismo
PROBABILIDAD_TRABAJO = 0.7  # 70% de probabilidad de hacer commits un día
MAX_COMMITS_POR_DIA = 5     # Cuántos commits máximos hacer (cambia la intensidad del verde)

print(f"Generando actividad orgánica desde {start_date.date()} hasta hoy...")

for i in range(days_to_run):
    current_date = start_date + timedelta(days=i)
    
    # Decidir aleatoriamente si se trabaja este día
    if random.random() < PROBABILIDAD_TRABAJO:
        # Decidir cuántos commits hacer hoy (entre 1 y 5)
        num_commits = random.randint(1, MAX_COMMITS_POR_DIA)
        
        for c in range(num_commits):
            # Añadimos unos minutos/segundos aleatorios para que no todos sean a la misma hora
            commit_time = current_date + timedelta(hours=random.randint(9, 18), minutes=random.randint(0, 59))
            formatted_date = commit_time.strftime('%Y-%m-%d %H:%M:%S')
            
            with open("data.txt", "a") as f:
                f.write(f"Log update: {formatted_date}\n")
            
            os.system('git add data.txt')
            # Comando para Windows (ajusta las fechas de autor y committer)
            os.system(f'set GIT_AUTHOR_DATE={formatted_date} && set GIT_COMMITTER_DATE={formatted_date} && git commit -m "Refactor: logic update {i}-{c}" --quiet')
    else:
        # Día de descanso (salto aleatorio)
        print(f"Saltando día: {current_date.date()}")

# 3. Subir resultados
print("\nSubiendo actividad orgánica a GitHub...")
os.system('git push origin main')