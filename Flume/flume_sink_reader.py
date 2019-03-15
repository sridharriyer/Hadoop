import os
from os.path import getsize

try:
    flume_sink_dir = 'C:/Users/SridharRamakrishnanI/Development/flume-sink'
    for file_name in os.listdir(flume_sink_dir):
        if not file_name.endswith('PROCESSED'):
            file_size = getsize(flume_sink_dir+'/'+file_name)
            if file_size == 0:
                os.remove(flume_sink_dir+'/'+file_name)
            else:
                print(file_size)
                os.rename(flume_sink_dir+'/'+file_name, flume_sink_dir+'/'+file_name+'.PROCESSED')
except IOError:
    print('File Error')
