import requests
from bs4 import BeautifulSoup
import cv2
import pyautogui
import webbrowser
import time

countInit = 0

def web(page,WebUrl,countInit):
    if(page>0):
        url = WebUrl
        code = requests.get(url)
        plain = code.text
        count = countInit
        #print(plain)
        s = BeautifulSoup(plain, "html.parser") #, {'jsname':'hSRGPd'}
        print (s.title.string)
        for link in s.find_all('img'):
            #tet = link.get('title')
            #print(tet)
            tet_2 = link.get('src')
            #print(tet_2)
            webbrowser.open(tet_2)

            ## Saves the image using pyautoGui
            screenSize = pyautogui.size()
            pyautogui.click(screenSize[0]/2,screenSize[1]/2)
            time.sleep(0.5)
            pyautogui.hotkey('ctrl', 's')
            time.sleep(0.5)
            food = "broccoli"
            food +=str(count)
            pyautogui.typewrite(food)
            time.sleep(0.1)
            pyautogui.press('enter')
            count = count + 1
            food +=".jpg"
            time.sleep(0.5)
            pyautogui.click(screenSize[0]/2,screenSize[1]/2)
            time.sleep(0.5)
            pyautogui.hotkey('ctrl', 'w')
            time.sleep(0.5)

            if count == 4:   ### REMOVE THIS ONCE TESTING IS COMPLETE
                break
            #imageCV = cv2.imread(food)
            #cv2.imshow("showing",imageCV)
            #cv2.waitKey(0)
url = "https://www.google.ca/search?q=broccoli&source=lnms&tbm=isch&sa=X&ved=0ahUKEwjf4Lmi26fdAhWwzlkKHQ2yCuQQ_AUICigB&biw=1920&bih=894"
web(1,url, countInit)