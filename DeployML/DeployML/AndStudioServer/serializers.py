from rest_framework import serializers
from .models import Addresses

class AddressesSerializers(serializers.ModelSerializer):
    class Meta:
        model = Addresses
        fields = ['email', 'password', 'name', 'introduce']

