from os import listdir
from os.path import isfile, join

from reportlab.graphics import renderPM
from svglib.svglib import svg2rlg

_dir = "."
_file_ending = ".svg"

files = [f for f in listdir(_dir) if isfile(join(_dir, f)) and f[-len(_file_ending):] == _file_ending]

for file in files:
    svg = svg2rlg(f"{_dir}/{file}")
    try:
        renderPM.drawToFile(svg, f"out/{file[:-len(_file_ending)]}.png", fmt="PNG")
    except:
        print("-- create 'out' directory")
