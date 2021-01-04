"""
To run:
    python GraphTraversal_Suresh.py <input_graph_file> <output_graph_file>
Ex: python GraphTraversal_Suresh.py small.txt graph-sol.txt


"""

import numpy as np
import sys


input_file = sys.argv[1]
output_file = sys.argv[2]

# Reading the input file
with open(input_file, 'r') as f:
    l = [[str(x) for x in line.split(' ')] for line in f]

#print(l)
newArr = l[1:][0:]
numarray = np.array(newArr)


numarray1 = numarray[:,0:len(l[1])-1]
#print(numarray1.shape)
normal_array = numarray1.tolist()

#print(numarray1)


row = len(normal_array)
column = len(normal_array[0])
# size of the 2D List
N = row* column

# Storing the input file elements into a matrix form as a Dictionary with attributes like Direction, color, adjacency list and num (for reference of position of elements)

for i in range(0, row):
    for j in range(0, column):
        if (normal_array[i][j]=='O'):
            adjacency_list = []  
            normal_array[i][j] = dict (direction = 'end', color = 'null', list = adjacency_list , num = row*column )
        else:
            x = normal_array[i][j].split("-")
            dir_temp = x[1]
            col_temp = x[0]
            adjacency_list = []
            normal_array[i][j] = dict (direction = dir_temp, color = col_temp, list = adjacency_list , num = None  )

inc = 1

for a in range(0, row):
    for b in range(0, column):
        normal_array[a][b]['num'] = inc
        inc = inc + 1

# Discovering Adjacency of the nodes using the conditions given on the project.

for m in range(0, row):
    for n in range(0, column):
        p = m
        q = n                                                                                
        t = normal_array[m][n]
        new_dir = t['direction']
        new_col = t['color'] 
        #new_lst = t['list']
        if(new_col == 'R'):
            if(new_dir == 'N'):
                p = p - 1
                while(p >= 0 ):
                    current = normal_array[p][q]
                    if(current['color'] == 'B' or current['color'] == 'null'):
                        tuple_list = normal_array[m][n]['list']
                        adj = normal_array[p][q]['num']
                        tuple_list.append(adj)
                    p = p - 1
            elif(new_dir == 'S'):
                p = p + 1
                while(p < row):
                    current = normal_array[p][q]
                    if(current['color'] == 'B' or current['color'] == 'null'):
                        tuple_list = normal_array[m][n]['list']
                        adj = normal_array[p][q]['num']
                        tuple_list.append(adj)
                    p = p + 1
            elif(new_dir == 'E'):
                q = q + 1
                while(q < column):
                    current = normal_array[p][q]
                    if(current['color'] == 'B' or current['color'] == 'null'):
                        tuple_list = normal_array[m][n]['list']
                        adj = normal_array[p][q]['num']
                        tuple_list.append(adj)
                    q = q + 1
            elif(new_dir == 'W'):
                q = q - 1
                while(q >= 0):
                    current = normal_array[p][q]
                    if(current['color'] == 'B' or current['color'] == 'null'):
                        tuple_list = normal_array[m][n]['list']
                        adj = normal_array[p][q]['num']
                        tuple_list.append(adj)
                    q = q - 1
            elif(new_dir == 'NE'):
                p = p - 1
                q = q + 1
                while(p >= 0 and q < column):
                    current = normal_array[p][q]
                    if(current['color'] == 'B' or current['color'] == 'null'):
                        tuple_list = normal_array[m][n]['list']
                        adj = normal_array[p][q]['num']
                        tuple_list.append(adj)
                    p = p - 1
                    q = q + 1
            elif(new_dir == 'NW'):
                p = p - 1
                q = q - 1
                while(p >= 0 and q >= 0):
                    current = normal_array[p][q]
                    if(current['color'] == 'B' or current['color'] == 'null'):
                        tuple_list = normal_array[m][n]['list']
                        adj = normal_array[p][q]['num']
                        tuple_list.append(adj)
                    p = p - 1
                    q = q - 1
            elif(new_dir == 'SE'):
                p = p + 1
                q = q + 1
                while(p < row and q < column):
                    current = normal_array[p][q]
                    if(current['color'] == 'B' or current['color'] == 'null'):
                        tuple_list = normal_array[m][n]['list']
                        adj = normal_array[p][q]['num']
                        tuple_list.append(adj)
                    p = p + 1
                    q = q + 1 
            elif(new_dir == 'SW'):
                p = p + 1
                q = q - 1
                while(p < row and q >= 0):
                    current = normal_array[p][q]
                    if(current['color'] == 'B' or current['color'] == 'null'):
                        tuple_list = normal_array[m][n]['list']
                        adj = normal_array[p][q]['num']
                        tuple_list.append(adj)
                    p = p + 1
                    q = q - 1

        elif(new_col == 'B'):
            if(new_dir == 'N'):
                p = p - 1
                while(p >= 0 ):
                    current = normal_array[p][q]
                    if(current['color'] == 'R' or current['color'] == 'null'):
                        tuple_list = normal_array[m][n]['list']
                        adj = normal_array[p][q]['num']
                        tuple_list.append(adj)
                    p = p - 1
            elif(new_dir == 'S'):
                p = p + 1
                while(p < row):
                    current = normal_array[p][q]
                    if(current['color'] == 'R' or current['color'] == 'null'):
                        tuple_list = normal_array[m][n]['list']
                        adj = normal_array[p][q]['num']
                        tuple_list.append(adj)
                    p = p + 1
            elif(new_dir == 'E'):
                q = q + 1
                while(q < column):
                    current = normal_array[p][q]
                    if(current['color'] == 'R' or current['color'] == 'null'):
                        tuple_list = normal_array[m][n]['list']
                        adj = normal_array[p][q]['num']
                        tuple_list.append(adj)
                    q = q + 1
            elif(new_dir == 'W'):
                q = q - 1
                while(q >= 0):
                    current = normal_array[p][q]
                    if(current['color'] == 'R' or current['color'] == 'null'):
                        tuple_list = normal_array[m][n]['list']
                        adj = normal_array[p][q]['num']
                        tuple_list.append(adj)
                    q = q - 1
            elif(new_dir == 'NE'):
                p = p - 1
                q = q + 1
                while(p >= 0 and q < column):
                    current = normal_array[p][q]
                    if(current['color'] == 'R' or current['color'] == 'null'):
                        tuple_list = normal_array[m][n]['list']
                        adj = normal_array[p][q]['num']
                        tuple_list.append(adj)
                    p = p - 1
                    q = q + 1
            elif(new_dir == 'NW'):
                p = p - 1
                q = q - 1
                while(p >= 0 and q >= 0):
                    current = normal_array[p][q]
                    if(current['color'] == 'R' or current['color'] == 'null'):
                        tuple_list = normal_array[m][n]['list']
                        adj = normal_array[p][q]['num']
                        tuple_list.append(adj)
                    p = p - 1
                    q = q - 1
            elif(new_dir == 'SE'):
                p = p + 1
                q = q + 1
                while(p < row and q < column):
                    current = normal_array[p][q]
                    if(current['color'] == 'R' or current['color'] == 'null'):
                        tuple_list = normal_array[m][n]['list']
                        adj = normal_array[p][q]['num']
                        tuple_list.append(adj)
                    p = p + 1
                    q = q + 1 
            elif(new_dir == 'SW'):
                p = p + 1
                q = q - 1
                while(p < row and q >= 0):
                    current = normal_array[p][q]
                    if(current['color'] == 'R' or current['color'] == 'null'):
                        tuple_list = normal_array[m][n]['list']
                        adj = normal_array[p][q]['num']
                        tuple_list.append(adj)
                    p = p + 1
                    q = q - 1




v = [[] for i in range(N)] 
  

def addEdge(x, y): 
    v[x].append(y) 
     
  
test_mat = np.arange(1,N+1).reshape(row, column)
a = test_mat.tolist()
d = dict( (j,(x, y)) for x, i in enumerate(a) for y, j in enumerate(i) )
indlist = []

#Function to get direction of the intermediate nodes 
def getDir(co1, co3):
    trav_dist = normal_array[co1][co3]['direction']
    #print(trav_dist)
    return trav_dist

# Function to print the nodes along with thier distance
def realPrint(dist, co1, co3):

    getdirec = getDir(co1,co3)
    if(getdirec != 'end'):
        print( str(dist) + getdirec, end=' ', file=open(output_file, "a"))

        
# Function to calculate the Hop distance between the nodes.
def printPF(stack):
    for data in stack:
        data1 = d[data]
        indlist.append(data1)
    #print(indlist [0])
    inlen = 0
    while(inlen < len(indlist) - 1):
        ele1 = indlist [inlen]
        ele2 = indlist [inlen+1]
        #print(ele1, ele2)
        co1 = ele1[0]
        co2 = ele2[0]
        co3 = ele1[1]
        co4 = ele2[1]
        #print(co1, co3, co2, co4 )
        if(co1 == co2 ):
            dist = abs(co3 - co4)
        elif(co3 == co4):
            dist = abs(co1 - co2)
        else:
            dist = abs(co1 - co2)
        realPrint(dist, co1, co3)
        inlen = inlen + 1

             
def path_finder_DFS(visited, x, y, stack): 
    stack.append(x) 
    if (x == y): 
        printPF(stack) 
        # exits the function when one complete path is found.
        exit()
    visited[x] = True
  
    if (len(v[x]) > 0):
        flag = 0 
        var = -1
        for j in v[x]: 
            flag = flag + 1  
            # if the node is not visited it finds the path for it.
            if (visited[j] == False): 
                path_finder_DFS(visited, j, y, stack) # recursive call for finding path
        if(flag == len(v[x]) and stack[-1] == var ):
            #visited[(stack[-1])] = False             
            del stack[-1]      
    del stack[-1] 
  
#call to path finding function with visited array for tracking
def path_finder_DFSHelper(x, y, n, stack): 
      
    visited = [0 for i in range(n + 1)] 
   
    path_finder_DFS(visited, x, y, stack) 



n = N # size of the visited array
stack = [] 


# iterating through the dictionary matrix's adjacency list and adding it to nodes
def addEdgeHelper(temp_num, Adj_list):
    if Adj_list:
        for itr in Adj_list:
            addEdge(temp_num,itr)

for m_temp in range(0, row):
    for n_temp in range(0, column):
        number = normal_array[m_temp][n_temp]['num']
        Adj_list = normal_array[m_temp][n_temp]['list']
        addEdgeHelper(number, Adj_list)
    

path_finder_DFSHelper(1, N, n, stack)

