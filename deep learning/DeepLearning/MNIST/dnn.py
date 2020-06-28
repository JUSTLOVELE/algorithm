import tensorflow as tf
from tensorflow import keras
from MNIST import  util
import numpy as np
import matplotlib.pyplot as plt

"""
也可以使用默认的数据包:
fashion_mnist = keras.datasets.fashion_mnist
(train_images, train_labels), (test_images, test_labels) = fashion_mnist.load_data()
"""
(train_images, train_labels), (test_images, test_labels)  = util.mnist_load_data("/Users/yangzuliang/Documents/dataset/FashionMNIST/raw")
class_names = ['T-shirt/top', 'Trouser', 'Pullover', 'Dress', 'Coat',
               'Sandal', 'Shirt', 'Sneaker', 'Bag', 'Ankle boot']
#设置图层
model = keras.Sequential([
    keras.layers.Flatten(input_shape=(28, 28)),
    #============细长model=============
    # keras.layers.Dense(5),
    # keras.layers.Dense(10),
    # keras.layers.Dense(10),
    # keras.layers.Dense(10),
    # keras.layers.Dense(10),
    # keras.layers.Dense(10),
    # ============一般长宽model=============
    # keras.layers.Dense(10),
    # keras.layers.Dense(18),
    # keras.layers.Dense(15),
    # keras.layers.Dense(10),
    # ============窄胖model=============
    keras.layers.Dense(128, activation='relu'),
    keras.layers.Dense(10),
])
#编译模型
model.compile(optimizer='adam',
              loss=tf.keras.losses.SparseCategoricalCrossentropy(from_logits=True),
              metrics=['accuracy'])
#训练模型
history = model.fit(train_images, train_labels, epochs=10)
print(history.history.keys())
plt.plot(history.history['loss'], label="loss")
plt.show()
plt.plot(history.history['accuracy'], label="accuracy")
plt.show()
#评估准确性
test_loss, test_acc = model.evaluate(test_images,  test_labels, verbose=2)
print('\nTest accuracy:', test_acc)
#预测
predictions = model.predict(test_images)
np.argmax(predictions[0])