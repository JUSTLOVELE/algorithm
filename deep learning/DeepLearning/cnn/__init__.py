import os

workspace_dir = 'C://dataset//lhy_data//hw3_hw5_hw7_2_and_7_3//food-11//training'

dirs = os.listdir(workspace_dir)

for file in dirs:
    print(file)