from django.urls import path
from . import views, MLmodel

urlpatterns = [
    path('',views.predictor),
    path('predict', views.formInfo)
]
