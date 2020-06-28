import torch
import numpy as np
"""
1.numpy和torch的tensor互相转换
2.torch的基本运算
3.求范数和均值
4.tensor.view的用法
"""

torch.manual_seed(446)
np.random.seed(446)

x_numpy = np.array([0.1,0.1,0.3])
x_torch = torch.tensor([0.1, 0.2, 0.3])
print('x_numpy, x_torch')
print(x_numpy, x_torch)
print("numpy转torch")
print(torch.from_numpy(x_numpy))
print("torch转numpy")
print(x_torch.numpy())
#基本运算
y_numpy = np.array([3,4,5.])
y_torch = torch.tensor([3,4,5.])
print(x_numpy + y_numpy, x_torch + y_torch)
print("norm")
print(np.linalg.norm(x_numpy), torch.norm(x_torch))
print("mean along the 0th dimension")
x_numpy = np.array([[1,2],[3,4.]])
x_torch = torch.tensor([[1,2],[3,4.]])
print(np.mean(x_numpy, axis=0), torch.mean(x_torch, dim=0))
N,C,W,H = 10000,3,28,28
X = torch.randn((N,C,W,H))
print(X.shape)
print(X.view(N, C, 784).shape)
#-1的话会自动选择第0个维度
print(X.view(-1, C, 784).shape)