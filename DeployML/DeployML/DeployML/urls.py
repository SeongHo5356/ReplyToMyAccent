from django.contrib import admin
from django.urls import path, include


urlpatterns = [
    #path('', include('DeployByREST.urls')),
    path('admin/', admin.site.urls),
    path('', include('AndStudioServer.urls'))

]
