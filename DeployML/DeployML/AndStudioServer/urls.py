from django.urls import path
from . import views

urlpatterns = [
    path('data/',views.data),
    path('app_data/', views.app_data)
]

