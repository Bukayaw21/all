import tkinter as tk
from tkinter import ttk

win = tk.Tk()
win.geometry("900x700+0+0")
win.config(bg="Aqua")
title_label = tk.Label(win, text="Pleas Login Carfully", font=("Arial", 30, "bold"), border=12, relief=tk.GROOVE, bg="blue", fg="yellow")
title_label.pack(side=tk.TOP, fill=tk.X)


Login_frame = tk.Frame(win, bg="Aqua",relief=tk.GROOVE)
Login_frame.place(x=250, y=150, width=400, height=100)

User_Name = tk.Label(Login_frame,text="User Name:",font=('arial',15),bg="Aqua", fg="blue")
User_Name.grid(row=1,column=0,padx=2,pady=2)
User_Name = tk.Entry(Login_frame,font=("arial",15),textvariable=User_Name )
User_Name.grid(row=1,column=1,padx=2,pady=2)

Password = tk.Label(Login_frame,text="Password:",font=('arial',15),bg="Aqua", fg="blue")
Password.grid(row=2,column=0,padx=2,pady=2)
Password = tk.Entry(Login_frame,font=("arial",15),textvariable=User_Name)
Password.grid(row=2,column=1,padx=2,pady=2)
  
btn = tk.Button(Login_frame, bg="lightblue", text="Login", font=("Arial", 13), width=13)
btn.grid(row=3, column=1, padx=10, pady=2)

frame = ttk.Frame(win)
frame.pack()
win.mainloop()