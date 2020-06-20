import tkinter as tk
from tkinter.filedialog import askopenfilename
from pridiction_test import forecast
import os
import sys
import ctypes

path = os.path.dirname(os.path.abspath(__file__))
sys.path.append(os.path.abspath(path))

def openFile():
    fileName = askopenfilename()
    
    if fileName.endswith("csv"):
        randomForestAccuracy, regressionAccuracy, nbAccuracy = forecast(fileName)
        ctypes.windll.user32.MessageBoxW(0, "Random Forest Accuracy: " + randomForestAccuracy + "\n" + "Logistic Regression Accuracy: " + regressionAccuracy + "\n" + "Gaussian Naive Bayes Accuracy: " + nbAccuracy + "\n", "Test Set Accuracy", 0)
    else:
        ctypes.windll.user32.MessageBoxW(0, "Not a csv file", "Test Set Accuracy", 0)

win = tk.Tk()

windowWidth = win.winfo_reqwidth()
windowHeight = win.winfo_reqheight()

positionRight = int(win.winfo_screenwidth()/2 - windowWidth/2)
positionDown = int(win.winfo_screenheight()/2 - windowHeight/2)
win.geometry("+{}+{}".format(positionRight, positionDown))

win.title("Football Match Forecast")
win.configure(background="white")

topFrame = tk.Frame(win)
topFrame.pack()


tk.Label(topFrame, text="Football Match Forecast").pack()
tk.Label(topFrame, text="Enter csv file to forecast:").pack()
tk.Button(topFrame, text="Open file", command=openFile).pack()


win.mainloop()