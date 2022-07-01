import cv2
import os

_dir = "./skins/1/"
_width = 20
_height = 20

files = [_dir + f for f in os.listdir(_dir) if ".png" in f]
for i in files:
    img = cv2.imread(i, cv2.IMREAD_UNCHANGED)
    res = cv2.resize(img, dsize=(_width, _height), interpolation=cv2.INTER_CUBIC)
    cv2.imwrite(i, res)
