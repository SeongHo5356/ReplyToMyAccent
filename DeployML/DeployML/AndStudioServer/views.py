from django.shortcuts import render
from django.http import JsonResponse
# Create your views here.

from rest_framework.parsers import JSONParser
from AndStudioServer.models import Addresses
from AndStudioServer.serializers import AddressesSerializers
from django.views.decorators.csrf import csrf_exempt
from django.utils.decorators import method_decorator


@method_decorator(csrf_exempt, name='dispatch')
def data(request):
    if request.method == 'GET':
        query_set = Addresses.objects.all()
        serializer = AddressesSerializers(query_set, many=True)
        return JsonResponse(serializer.data, safe=False)
    
    elif request.method == 'POST':
        data = JSONParser().parse(request)
        serializer = AddressesSerializers(data = data)
        if serializer.is_valid():
            serializer.save()
            return JsonResponse(serializer.data, status = 201)
        return JsonResponse(serializer.data, status = 400)

@method_decorator(csrf_exempt, name='dispatch')
def app_data(request):
    if request.method == 'GET':
        query_set = Addresses.objects.all()
        serializer = AddressesSerializers(query_set, many=True)
        return JsonResponse(serializer.data, safe=False)
    
    elif request.method == 'POST':
        email = request.POST.get('email', '')
        password = request.POST.get('password', '')
        name = request.POST.get('email', '')
        introduce = request.POST.get('introduce', '')
        data = {'email':email,'password':password,'email':name,'introduce':introduce}
        serializer = AddressesSerializers(data = data)
        if serializer.is_valid():
            serializer.save()
            return JsonResponse(serializer.data, status = 201)
        return JsonResponse(serializer.data, status = 400)
 