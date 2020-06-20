import pandas as pd
import sklearn
from keras.models import Sequential
from keras.layers import Dense
from keras.wrappers.scikit_learn import KerasClassifier
from sklearn.ensemble import RandomForestClassifier
from sklearn.model_selection import train_test_split
from sklearn.naive_bayes import GaussianNB
from sklearn.preprocessing import LabelEncoder
from sklearn.feature_selection import SelectKBest
from sklearn.feature_selection import chi2
from sklearn.linear_model import LogisticRegression
import pickle

# ------ PRE-PROCESS ------:
# import data
df = pd.read_csv("football_data.csv")
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

# split data to features and label
ds = df.values
X = ds[:, 0:38]
Y = ds[:, 39]

# split into test and train
X_train, X_test, y_train, y_test = train_test_split(X, Y, test_size=0.3, random_state=42)

# ------ CLASSIFY ------:

# create, fit, and evaluate random forest model
random_forest_model = RandomForestClassifier(n_estimators=100, min_samples_split=25, max_depth=7, max_features='auto')
random_forest_model.fit(X_train, y_train)
rfscores = random_forest_model.predict(X_test)
print("Random Forest accuracy: %.2f%%" % (sklearn.metrics.accuracy_score(y_test, rfscores) * 100))

# create, fit, and evaluate gaussian NB model
gaussian_NB_model = GaussianNB()
gaussian_NB_model.fit(X_train, y_train)
gnbscores = gaussian_NB_model.predict(X_test)
print("Gaussian NB accuracy: %.2f%%" % (sklearn.metrics.accuracy_score(y_test, gnbscores) * 100))

# create, fit, and evaluate Logistic Regression model
regression_model = LogisticRegression(max_iter=100000)
regression_model.fit(X_train, y_train)
rscores = regression_model.predict(X_test)
print("Logistic Regression accuracy: %.2f%%" % (sklearn.metrics.accuracy_score(y_test, rscores) * 100))

# ------ SAVE MODEL ------:
filename = 'random_forest_model.sav'
pickle.dump(random_forest_model, open(filename, 'wb'))

filename = 'gaussian_NB_model.sav'
pickle.dump(gaussian_NB_model, open(filename, 'wb'))

filename = 'regression_model.sav'
pickle.dump(regression_model, open(filename, 'wb'))


# serialize model to JSON - save the trained model for future use
# model_json = myModel.to_json()
# with open("model.json", "w") as json_file:
#    json_file.write(model_json)
# serialize weights to HDF5
# myModel.save_weights("model.h5")
# print("Saved model to disk")
# return model
# print the results
# print("Baseline: %.2f%% (%.2f%%)" % (results.mean() * 100, results.std() * 100))

"""
syntax to load the model
# load json and create model
json_file = open('model.json', 'r')
loaded_model_json = json_file.read()
json_file.close()
loaded_model = model_from_json(loaded_model_json)
# load weights into new model
loaded_model.load_weights("model.h5")
print("Loaded model from disk")

# evaluate loaded model on test data
loaded_model.compile(loss='binary_crossentropy', optimizer='rmsprop', metrics=['accuracy'])
score = loaded_model.evaluate(X, Y, verbose=0)
print("%s: %.2f%%" % (loaded_model.metrics_names[1], score[1]*100))
"""


