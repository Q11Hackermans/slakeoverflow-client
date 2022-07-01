import cv2
import os

_dir = "./skins/0/"
_width = 20
_height = 20

svg_files = [_dir + f for f in os.listdir(_dir) if ".png" in f]

for i in svg_files:
    img = cv2.imread(i)
    res = cv2.resize(img, dsize=(_width, _height), interpolation=cv2.INTER_CUBIC)

    cv2.imwrite(i, res)
