###### DRAW CONTOURS  #####
import imutils
import cv2
import numpy as np

# x = which column  ---> example
# y = which row --> example[0]

image = cv2.imread("C:\\Users\\dabes\\Desktop\\FitTrex\\BrocolliSkinnyWhiteBG.jpg")

imgray = cv2.cvtColor(image,cv2.COLOR_BGR2GRAY)
ret,thresh = cv2.threshold(imgray,202,255,0)
thresh = cv2.erode(thresh, None, iterations=2)
thresh = cv2.dilate(thresh, None, iterations=2)
threshList = (list(np.array(thresh)))
#print (len(threshList[0]))

leftMostPoint = 10000000
rightMostPoint = 0
upperMostPoint = 10000000
lowerMostPoint = 0
for x in range(len(threshList)):
	for y in range(len(threshList[0])):
		if threshList[x][y] < 250:
			if x < leftMostPoint:
				leftMostPoint = x
			elif x > rightMostPoint:
				rightMostPoint = x
			if y < upperMostPoint:
				upperMostPoint = y
			elif y > lowerMostPoint:
				lowerMostPoint = y

print(lowerMostPoint)
print(upperMostPoint)
print(rightMostPoint)
print(leftMostPoint)

cv2.rectangle(image, (lowerMostPoint,leftMostPoint ), (upperMostPoint, rightMostPoint), (255,0,0), 2)

cv2.imshow('imageOG', image)
cv2.imshow('thresh', thresh)
cv2.waitKey(0)