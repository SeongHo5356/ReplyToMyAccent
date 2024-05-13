from django.db import models

# Create your models here.
class Addresses(models.Model):
    name = models.CharField(max_length=10)
    email = models.CharField(max_length=40)
    password = models.CharField(max_length=20)
    introduce = models.TextField()
    