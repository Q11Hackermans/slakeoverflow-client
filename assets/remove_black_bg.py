import cv2
import os

_dir = "./skins/0/"
_width = 20
_height = 20

svg_files = [_dir + f for f in os.listdir(_dir) if ".png" in f]
for i in svg_files:
    src = cv2.imread(i, 1)
    tmp = cv2.cvtColor(src, cv2.COLOR_BGR2GRAY)
    _, alpha = cv2.threshold(tmp, 0, 255, cv2.THRESH_BINARY)
    b, g, r = cv2.split(src)
    rgba = [b, g, r, alpha]
    dst = cv2.merge(rgba, 4)
    cv2.imwrite(i, dst)
