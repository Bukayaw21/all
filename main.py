import tkinter as tk
from tkinter import ttk
from tkinter import messagebox
import pymysql
import mysql.connector

win = tk.Tk()
win.geometry("1350x700+0+0")
win.config(bg="blue")
title_label = tk.Label(win, text="Student Management System", font=("Arial", 30, "bold"), border=12, relief=tk.GROOVE, bg="blue", fg="yellow")
title_label.pack(side=tk.TOP, fill=tk.X)
detail_frame = tk.LabelFrame(win, text="Enter Details", font=("Arial", 30, "bold"), bg="lightgreen", fg="red",bd=12,relief=tk.GROOVE)
detail_frame.place(x=20, y=90, width=420, height=575)
data_frame = tk.Frame(win, bd=12,bg="fuchsia",relief=tk.GROOVE)
data_frame.place(x=440, y=90, width=810, height=575)


#===========Variable==========

rollno = tk.StringVar()
name = tk.StringVar()
gender = tk.StringVar()
age = tk.StringVar()
dep = tk.StringVar()
section = tk.StringVar()
address = tk.StringVar()
pob = tk.StringVar()
phone = tk.StringVar()
search_by = tk.StringVar()

#=============Input Lable========

rollno_lbl = tk.Label(detail_frame,text="Roll No:",font=('arial',15),bg="lightgreen")
rollno_lbl.grid(row=1,column=0,padx=2,pady=2)
rollno_ent = tk.Entry(detail_frame,font=("arial",15),textvariable=rollno)
rollno_ent.grid(row=1,column=1,padx=2,pady=2)

name_lbl = tk.Label(detail_frame,text="Name:",font=('arial',15),bg="lightgreen")
name_lbl.grid(row=2,column=0,padx=2,pady=2)
name_ent = tk.Entry(detail_frame,font=("arial",15),textvariable=name)
name_ent.grid(row=2,column=1,padx=2,pady=2)

gender_lbl = tk.Label(detail_frame,text="Gender:",font=('arial',15),bg="lightgreen")
gender_lbl.grid(row=3,column=0,padx=2,pady=2)
gender_ent = ttk.Combobox(detail_frame,font=("arial",15),textvariable=gender)
gender_ent['values'] = ("Male", "Female", "Others")
gender_ent.grid(row=3,column=1,padx=2,pady=2)

age_lbl = tk.Label(detail_frame,text="Age :",font=('arial',15),bg="lightgreen")
age_lbl.grid(row=4,column=0,padx=2,pady=2)
age_ent = tk.Entry(detail_frame,font=("arial",15),textvariable=age)
age_ent.grid(row=4,column=1,padx=2,pady=2)

dep_lbl = tk.Label(detail_frame,text="Department:",font=('arial',15),bg="lightgreen")
dep_lbl.grid(row=5,column=0,padx=2,pady=2)
dep_ent = tk.Entry(detail_frame,font=("arial",15),textvariable=dep)
dep_ent.grid(row=5,column=1,padx=2,pady=2)

section_lbl = tk.Label(detail_frame,text="Section:",font=('arial',15),bg="lightgreen")
section_lbl.grid(row=6,column=0,padx=2,pady=2)
section_ent = tk.Entry(detail_frame,font=("arial",15),textvariable=section)
section_ent.grid(row=6,column=1,padx=2,pady=2)

address_lbl = tk.Label(detail_frame,text="Address:",font=('arial',15),bg="lightgreen")
address_lbl.grid(row=7,column=0,padx=2,pady=2)
address_ent = tk.Entry(detail_frame,font=("arial",15),textvariable=address)
address_ent.grid(row=7,column=1,padx=2,pady=2)

pob_lbl = tk.Label(detail_frame,text="P.O.B:",font=('arial',15),bg="lightgreen")
pob_lbl.grid(row=8,column=0,padx=2,pady=2)
pob_ent = tk.Entry(detail_frame,font=("arial",15),textvariable=pob)
pob_ent.grid(row=8,column=1,padx=2,pady=2)

phone_lbl = tk.Label(detail_frame,text="Phone No:",font=('arial',15),bg="lightgreen")
phone_lbl.grid(row=9,column=0,padx=2,pady=2)
phone_ent = tk.Entry(detail_frame,font=("arial",15),textvariable=phone)
phone_ent.grid(row=9,column=1,padx=2,pady=2)

#==========Database function with MySQL database=========

connection = mysql.connector.connect(
    host="localhost",
    user="root",
    password="",
    database="engineering",
)

if connection.is_connected():

 def insert_data(rollno_var, name_var, gender_var, age_var, dep_var, section_var, address_var, pob_var, phone_var):
    conn = connection
    cursor = conn.cursor()
    try:
        # Check if any field is empty
        if any([not rollno_var.get(), not name_var.get(), not gender_var.get(), not age_var.get(), not dep_var.get(), not section_var.get(), not address_var.get(), not pob_var.get(), not phone_var.get()]):
            messagebox.showerror("Error", "All fields must be filled")
            return

        cursor.execute('INSERT INTO pystudent (rollno, name, gender, age, dep, section, address, pob, phone) VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s)',
                       (rollno_var.get(), name_var.get(), gender_var.get(), age_var.get(), dep_var.get(), section_var.get(), address_var.get(), pob_var.get(), phone_var.get()))
        conn.commit()
        messagebox.showinfo("Success", "Data inserted successfully!")
        clear_fields()  # Clear the entry fields
    except Exception as e:
        conn.rollback()
        messagebox.showerror("Error", f"Failed to insert data: {str(e)}")
        
    cursor.close()

def clear_fields():
    rollno.set("")
    name.set("")
    gender.set("")
    age.set("")
    dep.set("")
    section.set("")
    address.set("")
    pob.set("")
    phone.set("")


def delete_data(rollno_var):
    conn = connection
    cursor = conn.cursor()
    try:
        confirmation = messagebox.askyesno("Confirmation", "Are you sure you want to delete this record?")
        if not confirmation:
            return

        cursor.execute('DELETE FROM pystudent WHERE rollno = %s', (rollno_var,))
        conn.commit()
        messagebox.showinfo("Success", "Data deleted successfully!")
    except Exception as e:
        conn.rollback()
        messagebox.showerror("Error", f"Failed to delete data: {str(e)}")
    finally:
        cursor.close()

def update_data(rollno_var, name_var, gender_var, age_var, dep_var, section_var, address_var, pob_var, phone_var):
    conn = connection
    cursor = conn.cursor()
    try:
        cursor.execute('UPDATE pystudent SET name=%s, gender=%s, age=%s, dep=%s, section=%s, address=%s, pob=%s, phone=%s WHERE rollno=%s',
                        (name_var.get(), gender_var.get(), age_var.get(), dep_var.get(), section_var.get(), address_var.get(), pob_var.get(), phone_var.get(), rollno_var.get()))
        conn.commit()
        messagebox.showinfo("Success", "Data updated successfully!")
    except Exception as e:
        conn.rollback()
        messagebox.showerror("Error", f"Failed to update data: {str(e)}")
    cursor.close()

def clear_data():
    conn = connection  
    cursor = conn.cursor()
    cursor.execute('DELETE FROM pystudent')
    conn.commit()
    cursor.close()


#===========button================
    
btn_frame = tk.Frame(detail_frame, bg="teal", bd=10, relief=tk.GROOVE)
btn_frame.place(x=30,y=340,width=300,height=100)
add_btn = tk.Button(btn_frame, bg="fuchsia", text="Add", font=("Arial", 13), width=13, command=lambda: insert_data(
    rollno, name, gender, age, dep, section, address, pob, phone))
add_btn.grid(row=0, column=0, padx=2, pady=2)

delete_btn = tk.Button(btn_frame, bg="fuchsia", text="Delete", font=("Arial", 13), width=13, command=lambda: delete_data(rollno.get()))
delete_btn.grid(row=0, column=1, padx=2, pady=2)

update_btn = tk.Button(btn_frame, bg="fuchsia", text="Update", font=("Arial", 13), width=13, command=lambda: update_data(
    rollno, name, gender, age, dep, section, address, pob, phone))
update_btn.grid(row=1, column=0, padx=2, pady=2)

clear_btn = tk.Button(btn_frame, bg="fuchsia", text="Clear", font=("Arial", 13), width=13, command=clear_data)
clear_btn.grid(row=1, column=1, padx=2, pady=2)

#============Search ================

search_frame = tk.Frame(data_frame,bg="green",bd=10,relief="groove")
search_frame.pack(side=tk.TOP,fill=tk.X)
search_lbl = tk.Label(search_frame,text="search",bg="lightgrey",font=("Arial",14))
search_lbl.grid(row=0,column=0,padx=2,pady=2)
search_in = ttk.Combobox(search_frame,font=("Arial",14),state="readonly",textvariable=search_by)
search_in['values'] = ("Roll No","Name","Gender","Age","dep","Section","Address","P.O.B","phone No")
search_in.grid(row=0,column=1,padx=12,pady=2)
search_btn = tk.Button(search_frame, text="Search",font=("Arial",13),bd=9,width=14,bg="lightgrey")
search_btn.grid(row=0,column=2,padx=12,pady=2)
showall_btn = tk.Button(search_frame, text="Show All", font=("Arial", 13), bd=9, width=14, bg="lightgrey", command="show_all_data")
showall_btn.grid(row=0, column=3, padx=12, pady=2)

#============Database Frame=============

main_frame = tk.Frame(data_frame,bg="lightgrey",bd=11,relief=tk.GROOVE)
main_frame.pack(fill=tk.BOTH,expand=True)
y_scroll = tk.Scrollbar(main_frame,orient=tk.VERTICAL)
x_scroll = tk.Scrollbar(main_frame,orient=tk.HORIZONTAL)
'''"Roll No","Name","Gender","Age","dep","Section","Address","P.O.B","phone No" '''
student_table = ttk.Treeview(main_frame,columns=("Roll No","Name","Gender","Age","dep","Section","Address","P.O.B","Phone No"),
                             yscrollcommand=y_scroll.set,xscrollcommand=x_scroll.set)
y_scroll.config(command=student_table.yview)
x_scroll.config(command=student_table.xview)
y_scroll.pack(side=tk.RIGHT,fill=tk.Y)
x_scroll.pack(side=tk.BOTTOM,fill=tk.X)
student_table.heading("Roll No", text="Roll No")
student_table.heading("Name", text="Name")
student_table.heading("Gender", text="Gender")
student_table.heading("Age", text="Age")
student_table.heading("dep", text="Department")
student_table.heading("Section", text="Section")
student_table.heading("Address", text="Address")
student_table.heading("P.O.B", text="P.O.B")
student_table.heading("Phone No", text="Phone No")
student_table['show'] = 'headings'


student_table.column("Roll No",width=100)
student_table.column("Name", width=100)
student_table.column("Gender", width=100)
student_table.column("Age", width=100)
student_table.column("dep", width=100)
student_table.column("Section", width=100)
student_table.column("Address", width=100)
student_table.column("P.O.B", width=100)
student_table.column("Phone No", width=100)


student_table.pack(fill=tk.BOTH,expand=True)

# Update your existing fetch_data function to populate the student_table:
def fetch_data():
    conn = connection
    cursor = conn.cursor()
    cursor.execute('SELECT * FROM pystudent')
    data = cursor.fetchall()
    cursor.close()
    return data
data = fetch_data()
for row in data:
    student_table.insert('', 'end', values=row)

# Add this code snippet before the win.mainloop() at the end of your script.
#============Database Frame=============

frame = ttk.Frame(win)
frame.pack()
win.mainloop()