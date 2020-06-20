import pickle
import pandas as pd
import sklearn
from sklearn.preprocessing import LabelEncoder
import os
import sys

# Fixed path for opening csv file
def fixPath(path):
    return path.replace('\\', '/')

path = fixPath(os.path.dirname(os.path.abspath(__file__)))
sys.path.append(os.path.abspath(path))

# load and pre process test set
def forecast(filePath):
    df = pd.read_csv(filePath)

    # encode categorical values using LabelEncoder, and normalize numeric values
    le = LabelEncoder()
    for column_name in df.columns:
        if df[column_name].dtypes == object:
            df[column_name] = le.fit_transform(df[column_name])
        elif 'overall' in column_name:
            df.dropna(subset=[column_name], inplace=True)
        else:
            df.fillna(df[column_name].mean(), inplace=True)
            max_value = df[column_name].max()
            min_value = df[column_name].min()
            df[column_name] = (df[column_name] - min_value) / (max_value - min_value)
            df[column_name] = pd.cut(df[column_name], 5)
            df[column_name] = le.fit_transform(df[column_name])

    # spit data to features and label
    ds = df.values
    X = ds[:, 0:38]
    Y = ds[:, 39]

    averageType = 'macro'

    # load trained models
    filename = path + '/random_forest_model.sav'
    loaded_model = pickle.load(open(filename, 'rb'))
    # predict on test set
    result = loaded_model.predict(X)
    #print("Random Forest (" + averageType +"):")
    randomForestAccuracy = "%.2f%%" % (sklearn.metrics.accuracy_score(Y, result) * 100)
    #print("Test Set Accuracy: %.2f%%" % (sklearn.metrics.accuracy_score(Y, result) * 100))
    #print("F Measure: %.2f%%" % (sklearn.metrics.f1_score(Y, result, average=averageType) * 100))
    #print("Jaccard: %.2f%%" % (sklearn.metrics.jaccard_score(Y, result, average=averageType) * 100))
    #print("Recall: %.2f%%" % (sklearn.metrics.recall_score(Y, result, average=averageType) * 100))
    #print("Precision: %.2f%%" % (sklearn.metrics.precision_score(Y, result, average=averageType) * 100))
    #print("\n")

    filename = path + '/regression_model.sav'
    loaded_model = pickle.load(open(filename, 'rb'))
    # predict on test set
    result = loaded_model.predict(X)
    #print("Logistic Regression (" + averageType +"):")
    regressionAccuracy = "%.2f%%" % (sklearn.metrics.accuracy_score(Y, result) * 100)
    #print("Test Set Accuracy: %.2f%%" % (sklearn.metrics.accuracy_score(Y, result) * 100))
    #print("F Measure: %.2f%%" % (sklearn.metrics.recall_score(Y, result, average=averageType) * 100))
    #print("Jaccard: %.2f%%" % (sklearn.metrics.jaccard_score(Y, result, average=averageType) * 100))
    #print("Recall: %.2f%%" % (sklearn.metrics.recall_score(Y, result, average=averageType) * 100))
    #print("Precision: %.2f%%" % (sklearn.metrics.precision_score(Y, result, average=averageType) * 100))
    #print("\n")

    filename = path + '/gaussian_NB_model.sav'
    loaded_model = pickle.load(open(filename, 'rb'))
    # predict on test set
    result = loaded_model.predict(X)
    #print("Gaussian Naive Bayes (" + averageType +"):")
    nbAccuracy = "%.2f%%" % (sklearn.metrics.accuracy_score(Y, result) * 100)
    #print("Test Set Accuracy: %.2f%%" % (sklearn.metrics.accuracy_score(Y, result) * 100))
    #print("F Measure: %.2f%%" % (sklearn.metrics.recall_score(Y, result, average=averageType) * 100))
    #print("Jaccard: %.2f%%" % (sklearn.metrics.jaccard_score(Y, result, average=averageType) * 100))
    #print("Recall: %.2f%%" % (sklearn.metrics.recall_score(Y, result, average=averageType) * 100))
    #print("Precision: %.2f%%" % (sklearn.metrics.precision_score(Y, result, average=averageType) * 100))

    return randomForestAccuracy, regressionAccuracy, nbAccuracy


