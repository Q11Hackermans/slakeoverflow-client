import os

svg_files = [f for f in os.listdir(".") if f.endswith(".svg")]

"""
using convert command on linux; might have to install potrace
(https://aur.archlinux.org/packages/python-pypotrace-git)
"""

for svg_file in svg_files:
    png_file = svg_file.replace(".svg", ".png")
    os.system(cmd=f"convert {svg_file} {png_file}")
