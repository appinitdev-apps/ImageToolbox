import os
from datetime import datetime, timedelta

# Configura el inicio y fin de tu "actividad"
start_date = datetime(2025, 5, 1)
for i in range(365):
    current_date = start_date + timedelta(days=i)
    # Formato de fecha para Git
    formatted_date = current_date.strftime('%Y-%m-%d %H:%M:%S')
    
    # Crea un cambio y haz el commit con la fecha falsa
    os.system(f'echo "update {i}" > data.txt')
    os.system(f'git add .')
    os.system(f'git commit --date="{formatted_date}" -m "commit {i}"')

os.system('git push origin main')