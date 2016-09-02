	.text
.globl printArray
printArray:
# Save registers
	pushq %rbx
	pushq %r10
	pushq %r13
	pushq %r14
	pushq %r15
	subq $128,%rsp
	#Save arguments
	movq %rdi,128(%rsp)
	movq %rsi,120(%rsp)
	movq %rdx,112(%rsp)
	#Push Local var left
	movq 120(%rsp), %rbx
	#Assign to Local var i
	movq %rbx, 104(%rsp)

	# FOR START
twirl_start_0:
	#Push Local var i
	movq 104(%rsp), %rbx
	#Push Local var right
	movq 112(%rsp), %r10

	# <=
	cmpq %r10,%rbx
movq $0, %r10
movq $0, %rbx
movq $1, %rdi
	cmovle %rdi, %rbx
	cmpq $0, %rbx
	jle twirl_end_0
	jmp for_assign_0
for_expression_0:
continue_point_0:
	#Push Local var i
	movq 104(%rsp), %rbx

	# push 1
	movq $1,%r10

	# +
	addq %r10,%rbx
	#Assign to Local var i
	movq %rbx, 104(%rsp)
	jmp twirl_start_0
for_assign_0:
	#top=0

	# push string "%d: %s\n" top=0
	movq $string0, %rbx
	#Push Local var i
	movq 104(%rsp), %r10
	#Push Local var i
	movq 104(%rsp), %r13
	#Assign2 to Local var a
			 ### type 8
	movq %r13, %rax
	imulq $8, %rax
	addq 128(%rsp), %rax
	movq (%rax), %r13
     # func=printf nargs=3
     # Move values from reg stack to reg args
	movq %r13, %rdx
	movq %r10, %rsi
	movq %rbx, %rdi
	movl    $0, %eax
	call printf
	movq %rax, %rbx
	jmp for_expression_0
twirl_end_0:

	# FOR END
	addq $128,%rsp
# Restore registers
	popq %r15
	popq %r14
	popq %r13
	popq %r10
	popq %rbx
	ret
	.text
.globl print
print:
# Save registers
	pushq %rbx
	pushq %r10
	pushq %r13
	pushq %r14
	pushq %r15
	subq $128,%rsp
	#Save arguments
	movq %rdi,128(%rsp)
	#top=0

	# push string "==%s==\n" top=0
	movq $string1, %rbx
	#Push Local var s
	movq 128(%rsp), %r10
     # func=printf nargs=2
     # Move values from reg stack to reg args
	movq %r10, %rsi
	movq %rbx, %rdi
	movl    $0, %eax
	call printf
	movq %rax, %rbx
	addq $128,%rsp
# Restore registers
	popq %r15
	popq %r14
	popq %r13
	popq %r10
	popq %rbx
	ret
	.text
.globl mystrcmp
mystrcmp:
# Save registers
	pushq %rbx
	pushq %r10
	pushq %r13
	pushq %r14
	pushq %r15
	subq $128,%rsp
	#Save arguments
	movq %rdi,128(%rsp)
	movq %rsi,120(%rsp)

	# WHILE STart
twirl_start_1:
continue_point_1:

	# push 0
	movq $0,%rbx
	#Assign2 to Local var s1
			 ### type 1
	movq %rbx, %rax
	imulq $1, %rax
	addq 128(%rsp), %rax
	movb (%rax), %cl
	movq %rcx, %rbx
	movq $0, %rcx

	# push 0
	movq $0,%r10
	#Assign2 to Local var s2
			 ### type 1
	movq %r10, %rax
	imulq $1, %rax
	addq 120(%rsp), %rax
	movb (%rax), %cl
	movq %rcx, %r10
	movq $0, %rcx

	# &&
	imulq %r10,%rbx

	# push 0
	movq $0,%r10
	#Assign2 to Local var s1
			 ### type 1
	movq %r10, %rax
	imulq $1, %rax
	addq 128(%rsp), %rax
	movb (%rax), %cl
	movq %rcx, %r10
	movq $0, %rcx

	# push 0
	movq $0,%r13
	#Assign2 to Local var s2
			 ### type 1
	movq %r13, %rax
	imulq $1, %rax
	addq 120(%rsp), %rax
	movb (%rax), %cl
	movq %rcx, %r13
	movq $0, %rcx

	# ==
	cmpq %r13,%r10
movq $0, %r13
movq $0, %r10
movq $1, %rdi
	cmove %rdi, %r10

	# &&
	imulq %r10,%rbx
	cmpq $0, %rbx
	je twirl_end_1
	#Push Local var s1
	movq 128(%rsp), %rbx

	# push 1
	movq $1,%r10

	# +
	addq %r10,%rbx
	#Assign to Local var s1
	movq %rbx, 128(%rsp)
	#Push Local var s2
	movq 120(%rsp), %rbx

	# push 1
	movq $1,%r10

	# +
	addq %r10,%rbx
	#Assign to Local var s2
	movq %rbx, 120(%rsp)
	jmp twirl_start_1
twirl_end_1:

	# WHILE END
if_start_2:

	# push 0
	movq $0,%rbx
	#Assign2 to Local var s1
			 ### type 1
	movq %rbx, %rax
	imulq $1, %rax
	addq 128(%rsp), %rax
	movb (%rax), %cl
	movq %rcx, %rbx
	movq $0, %rcx

	# push 0
	movq $0,%r10

	# ==
	cmpq %r10,%rbx
movq $0, %r10
movq $0, %rbx
movq $1, %rdi
	cmove %rdi, %rbx

	# push 0
	movq $0,%r10
	#Assign2 to Local var s2
			 ### type 1
	movq %r10, %rax
	imulq $1, %rax
	addq 120(%rsp), %rax
	movb (%rax), %cl
	movq %rcx, %r10
	movq $0, %rcx

	# push 0
	movq $0,%r13

	# ==
	cmpq %r13,%r10
movq $0, %r13
movq $0, %r10
movq $1, %rdi
	cmove %rdi, %r10

	# &&
	imulq %r10,%rbx
	cmpq $0, %rbx
	je if_end_2

	# push 0
	movq $0,%rbx
	movq %rbx, %rax
	addq $128,%rsp
	popq %r15
	popq %r14
	popq %r13
	popq %r10
	popq %rbx
	ret
	jmp else_end_2
if_end_2:
else_end_2:
if_start_3:

	# push 0
	movq $0,%rbx
	#Assign2 to Local var s1
			 ### type 1
	movq %rbx, %rax
	imulq $1, %rax
	addq 128(%rsp), %rax
	movb (%rax), %cl
	movq %rcx, %rbx
	movq $0, %rcx

	# push 0
	movq $0,%r10

	# ==
	cmpq %r10,%rbx
movq $0, %r10
movq $0, %rbx
movq $1, %rdi
	cmove %rdi, %rbx

	# push 0
	movq $0,%r10
	#Assign2 to Local var s2
			 ### type 1
	movq %r10, %rax
	imulq $1, %rax
	addq 120(%rsp), %rax
	movb (%rax), %cl
	movq %rcx, %r10
	movq $0, %rcx

	# push 0
	movq $0,%r13

	# !=
	cmpq %r13,%r10
movq $0, %r13
movq $0, %r10
movq $1, %rdi
	cmovne %rdi, %r10

	# &&
	imulq %r10,%rbx
	cmpq $0, %rbx
	je if_end_3

	# push -1
	movq $-1,%rbx
	movq %rbx, %rax
	addq $128,%rsp
	popq %r15
	popq %r14
	popq %r13
	popq %r10
	popq %rbx
	ret
	jmp else_end_3
if_end_3:
else_end_3:
if_start_4:

	# push 0
	movq $0,%rbx
	#Assign2 to Local var s1
			 ### type 1
	movq %rbx, %rax
	imulq $1, %rax
	addq 128(%rsp), %rax
	movb (%rax), %cl
	movq %rcx, %rbx
	movq $0, %rcx

	# push 0
	movq $0,%r10

	# !=
	cmpq %r10,%rbx
movq $0, %r10
movq $0, %rbx
movq $1, %rdi
	cmovne %rdi, %rbx

	# push 0
	movq $0,%r10
	#Assign2 to Local var s2
			 ### type 1
	movq %r10, %rax
	imulq $1, %rax
	addq 120(%rsp), %rax
	movb (%rax), %cl
	movq %rcx, %r10
	movq $0, %rcx

	# push 0
	movq $0,%r13

	# ==
	cmpq %r13,%r10
movq $0, %r13
movq $0, %r10
movq $1, %rdi
	cmove %rdi, %r10

	# &&
	imulq %r10,%rbx
	cmpq $0, %rbx
	je if_end_4

	# push 1
	movq $1,%rbx
	movq %rbx, %rax
	addq $128,%rsp
	popq %r15
	popq %r14
	popq %r13
	popq %r10
	popq %rbx
	ret
	jmp else_end_4
if_end_4:
else_end_4:
if_start_5:

	# push 0
	movq $0,%rbx
	#Assign2 to Local var s1
			 ### type 1
	movq %rbx, %rax
	imulq $1, %rax
	addq 128(%rsp), %rax
	movb (%rax), %cl
	movq %rcx, %rbx
	movq $0, %rcx

	# push 0
	movq $0,%r10
	#Assign2 to Local var s2
			 ### type 1
	movq %r10, %rax
	imulq $1, %rax
	addq 120(%rsp), %rax
	movb (%rax), %cl
	movq %rcx, %r10
	movq $0, %rcx

	# Great
	cmpq %r10,%rbx
movq $0, %r10
movq $0, %rbx
movq $1, %rdi
	cmovg %rdi, %rbx
	cmpq $0, %rbx
	je if_end_5

	# push 1
	movq $1,%rbx
	movq %rbx, %rax
	addq $128,%rsp
	popq %r15
	popq %r14
	popq %r13
	popq %r10
	popq %rbx
	ret
	jmp else_end_5
if_end_5:
else_end_5:

	# push -1
	movq $-1,%rbx
	movq %rbx, %rax
	addq $128,%rsp
	popq %r15
	popq %r14
	popq %r13
	popq %r10
	popq %rbx
	ret
	addq $128,%rsp
# Restore registers
	popq %r15
	popq %r14
	popq %r13
	popq %r10
	popq %rbx
	ret
	.text
.globl quicksortsubrange
quicksortsubrange:
# Save registers
	pushq %rbx
	pushq %r10
	pushq %r13
	pushq %r14
	pushq %r15
	subq $128,%rsp
	#Save arguments
	movq %rdi,128(%rsp)
	movq %rsi,120(%rsp)
	movq %rdx,112(%rsp)
if_start_6:
	#Push Local var left
	movq 120(%rsp), %rbx
	#Push Local var right
	movq 112(%rsp), %r10

	# >=
	cmpq %r10,%rbx
movq $0, %r10
movq $0, %rbx
movq $1, %rdi
	cmovge %rdi, %rbx
	cmpq $0, %rbx
	je if_end_6

	# push 0
	movq $0,%rbx
	movq %rbx, %rax
	addq $128,%rsp
	popq %r15
	popq %r14
	popq %r13
	popq %r10
	popq %rbx
	ret
	jmp else_end_6
if_end_6:
else_end_6:
	#Push Local var right
	movq 112(%rsp), %rbx
	#Assign2 to Local var a
			 ### type 8
	movq %rbx, %rax
	imulq $8, %rax
	addq 128(%rsp), %rax
	movq (%rax), %rbx
	#Assign to Local var x
	movq %rbx, 104(%rsp)
	#Push Local var left
	movq 120(%rsp), %rbx
	#Assign to Local var l
	movq %rbx, 96(%rsp)
	#Push Local var right
	movq 112(%rsp), %rbx

	# push 1
	movq $1,%r10

	# -
	subq %r10,%rbx
	#Assign to Local var g
	movq %rbx, 88(%rsp)

	# WHILE STart
twirl_start_7:
continue_point_2:
	#Push Local var l
	movq 96(%rsp), %rbx
	#Push Local var g
	movq 88(%rsp), %r10

	# less
	cmpq %r10,%rbx
movq $0, %r10
movq $0, %rbx
movq $1, %rdi
	cmovl %rdi, %rbx
	cmpq $0, %rbx
	je twirl_end_7

	# WHILE STart
twirl_start_8:
continue_point_3:
	#Push Local var l
	movq 96(%rsp), %rbx
	#Push Local var g
	movq 88(%rsp), %r10

	# less
	cmpq %r10,%rbx
movq $0, %r10
movq $0, %rbx
movq $1, %rdi
	cmovl %rdi, %rbx
	#Push Local var l
	movq 96(%rsp), %r10
	#Assign2 to Local var a
			 ### type 8
	movq %r10, %rax
	imulq $8, %rax
	addq 128(%rsp), %rax
	movq (%rax), %r10
	#Push Local var x
	movq 104(%rsp), %r13
     # func=mystrcmp nargs=2
     # Move values from reg stack to reg args
	movq %r13, %rsi
	movq %r10, %rdi
	call mystrcmp
	movq %rax, %r10

	# push 0
	movq $0,%r13

	# less
	cmpq %r13,%r10
movq $0, %r13
movq $0, %r10
movq $1, %rdi
	cmovl %rdi, %r10

	# &&
	imulq %r10,%rbx
	cmpq $0, %rbx
	je twirl_end_8
	#Push Local var l
	movq 96(%rsp), %rbx

	# push 1
	movq $1,%r10

	# +
	addq %r10,%rbx
	#Assign to Local var l
	movq %rbx, 96(%rsp)
	jmp twirl_start_8
twirl_end_8:

	# WHILE END

	# WHILE STart
twirl_start_9:
continue_point_4:
	#Push Local var l
	movq 96(%rsp), %rbx
	#Push Local var g
	movq 88(%rsp), %r10

	# less
	cmpq %r10,%rbx
movq $0, %r10
movq $0, %rbx
movq $1, %rdi
	cmovl %rdi, %rbx
	#Push Local var g
	movq 88(%rsp), %r10
	#Assign2 to Local var a
			 ### type 8
	movq %r10, %rax
	imulq $8, %rax
	addq 128(%rsp), %rax
	movq (%rax), %r10
	#Push Local var x
	movq 104(%rsp), %r13
     # func=mystrcmp nargs=2
     # Move values from reg stack to reg args
	movq %r13, %rsi
	movq %r10, %rdi
	call mystrcmp
	movq %rax, %r10

	# push 0
	movq $0,%r13

	# >=
	cmpq %r13,%r10
movq $0, %r13
movq $0, %r10
movq $1, %rdi
	cmovge %rdi, %r10

	# &&
	imulq %r10,%rbx
	cmpq $0, %rbx
	je twirl_end_9
	#Push Local var g
	movq 88(%rsp), %rbx

	# push 1
	movq $1,%r10

	# -
	subq %r10,%rbx
	#Assign to Local var g
	movq %rbx, 88(%rsp)
	jmp twirl_start_9
twirl_end_9:

	# WHILE END
if_start_10:
	#Push Local var l
	movq 96(%rsp), %rbx
	#Push Local var g
	movq 88(%rsp), %r10

	# less
	cmpq %r10,%rbx
movq $0, %r10
movq $0, %rbx
movq $1, %rdi
	cmovl %rdi, %rbx
	cmpq $0, %rbx
	je if_end_10
	#Push Local var l
	movq 96(%rsp), %rbx
	#Assign2 to Local var a
			 ### type 8
	movq %rbx, %rax
	imulq $8, %rax
	addq 128(%rsp), %rax
	movq (%rax), %rbx
	#Assign to Local var tmp
	movq %rbx, 80(%rsp)
	#Push Local var l
	movq 96(%rsp), %rbx
	#Push Local var g
	movq 88(%rsp), %r10
	#Assign2 to Local var a
			 ### type 8
	movq %r10, %rax
	imulq $8, %rax
	addq 128(%rsp), %rax
	movq (%rax), %r10
	#Assign to Local var check heere 12 a
			 ### type 128
	movq %rbx, %rax
	imulq $8, %rax
	addq 128(%rsp), %rax
	movq %r10, (%rax)
	#Push Local var g
	movq 88(%rsp), %rbx
	#Push Local var tmp
	movq 80(%rsp), %r10
	#Assign to Local var check heere 12 a
			 ### type 128
	movq %rbx, %rax
	imulq $8, %rax
	addq 128(%rsp), %rax
	movq %r10, (%rax)
	jmp else_end_10
if_end_10:
else_end_10:
	jmp twirl_start_7
twirl_end_7:

	# WHILE END
	#Push Local var right
	movq 112(%rsp), %rbx
	#Push Local var l
	movq 96(%rsp), %r10
	#Assign2 to Local var a
			 ### type 8
	movq %r10, %rax
	imulq $8, %rax
	addq 128(%rsp), %rax
	movq (%rax), %r10
	#Assign to Local var check heere 12 a
			 ### type 128
	movq %rbx, %rax
	imulq $8, %rax
	addq 128(%rsp), %rax
	movq %r10, (%rax)
	#Push Local var l
	movq 96(%rsp), %rbx
	#Push Local var x
	movq 104(%rsp), %r10
	#Assign to Local var check heere 12 a
			 ### type 128
	movq %rbx, %rax
	imulq $8, %rax
	addq 128(%rsp), %rax
	movq %r10, (%rax)
	#Push Local var a
	movq 128(%rsp), %rbx
	#Push Local var left
	movq 120(%rsp), %r10
	#Push Local var l
	movq 96(%rsp), %r13

	# push 1
	movq $1,%r14

	# -
	subq %r14,%r13
     # func=quicksortsubrange nargs=3
     # Move values from reg stack to reg args
	movq %r13, %rdx
	movq %r10, %rsi
	movq %rbx, %rdi
	call quicksortsubrange
	movq %rax, %rbx
	#Push Local var a
	movq 128(%rsp), %rbx
	#Push Local var g
	movq 88(%rsp), %r10

	# push 1
	movq $1,%r13

	# +
	addq %r13,%r10
	#Push Local var right
	movq 112(%rsp), %r13
     # func=quicksortsubrange nargs=3
     # Move values from reg stack to reg args
	movq %r13, %rdx
	movq %r10, %rsi
	movq %rbx, %rdi
	call quicksortsubrange
	movq %rax, %rbx
	addq $128,%rsp
# Restore registers
	popq %r15
	popq %r14
	popq %r13
	popq %r10
	popq %rbx
	ret
	.text
.globl quicksort
quicksort:
# Save registers
	pushq %rbx
	pushq %r10
	pushq %r13
	pushq %r14
	pushq %r15
	subq $128,%rsp
	#Save arguments
	movq %rdi,128(%rsp)
	movq %rsi,120(%rsp)
	#Push Local var a
	movq 128(%rsp), %rbx

	# push 0
	movq $0,%r10
	#Push Local var n
	movq 120(%rsp), %r13

	# push 1
	movq $1,%r14

	# -
	subq %r14,%r13
     # func=quicksortsubrange nargs=3
     # Move values from reg stack to reg args
	movq %r13, %rdx
	movq %r10, %rsi
	movq %rbx, %rdi
	call quicksortsubrange
	movq %rax, %rbx
	addq $128,%rsp
# Restore registers
	popq %r15
	popq %r14
	popq %r13
	popq %r10
	popq %rbx
	ret
	.text
.globl main
main:
# Save registers
	pushq %rbx
	pushq %r10
	pushq %r13
	pushq %r14
	pushq %r15
	subq $128,%rsp
	#Save arguments

	# push 6
	movq $6,%rbx
	#Assign to Local var n
	movq %rbx, 128(%rsp)
	#Push Local var n
	movq 128(%rsp), %rbx

	# push 8
	movq $8,%r10

	# *
	imulq %r10,%rbx
     # func=malloc nargs=1
     # Move values from reg stack to reg args
	movq %rbx, %rdi
	call malloc
	movq %rax, %rbx
	#Assign to Local var a
	movq %rbx, 120(%rsp)

	# push 0
	movq $0,%rbx
	#top=1

	# push string "Rachael" top=1
	movq $string2, %r10
	#Assign to Local var check heere 12 a
			 ### type 120
	movq %rbx, %rax
	imulq $8, %rax
	addq 120(%rsp), %rax
	movq %r10, (%rax)

	# push 1
	movq $1,%rbx
	#top=1

	# push string "Monica" top=1
	movq $string3, %r10
	#Assign to Local var check heere 12 a
			 ### type 120
	movq %rbx, %rax
	imulq $8, %rax
	addq 120(%rsp), %rax
	movq %r10, (%rax)

	# push 2
	movq $2,%rbx
	#top=1

	# push string "Phoebe" top=1
	movq $string4, %r10
	#Assign to Local var check heere 12 a
			 ### type 120
	movq %rbx, %rax
	imulq $8, %rax
	addq 120(%rsp), %rax
	movq %r10, (%rax)

	# push 3
	movq $3,%rbx
	#top=1

	# push string "Joey" top=1
	movq $string5, %r10
	#Assign to Local var check heere 12 a
			 ### type 120
	movq %rbx, %rax
	imulq $8, %rax
	addq 120(%rsp), %rax
	movq %r10, (%rax)

	# push 4
	movq $4,%rbx
	#top=1

	# push string "Ross" top=1
	movq $string6, %r10
	#Assign to Local var check heere 12 a
			 ### type 120
	movq %rbx, %rax
	imulq $8, %rax
	addq 120(%rsp), %rax
	movq %r10, (%rax)

	# push 5
	movq $5,%rbx
	#top=1

	# push string "Chandler" top=1
	movq $string7, %r10
	#Assign to Local var check heere 12 a
			 ### type 120
	movq %rbx, %rax
	imulq $8, %rax
	addq 120(%rsp), %rax
	movq %r10, (%rax)
	#top=0

	# push string "-------- Before -------\n" top=0
	movq $string8, %rbx
     # func=printf nargs=1
     # Move values from reg stack to reg args
	movq %rbx, %rdi
	movl    $0, %eax
	call printf
	movq %rax, %rbx
	#Push Local var a
	movq 120(%rsp), %rbx

	# push 0
	movq $0,%r10
	#Push Local var n
	movq 128(%rsp), %r13

	# push 1
	movq $1,%r14

	# -
	subq %r14,%r13
     # func=printArray nargs=3
     # Move values from reg stack to reg args
	movq %r13, %rdx
	movq %r10, %rsi
	movq %rbx, %rdi
	call printArray
	movq %rax, %rbx
	#Push Local var a
	movq 120(%rsp), %rbx
	#Push Local var n
	movq 128(%rsp), %r10
     # func=quicksort nargs=2
     # Move values from reg stack to reg args
	movq %r10, %rsi
	movq %rbx, %rdi
	call quicksort
	movq %rax, %rbx
	#top=0

	# push string "-------- After -------\n" top=0
	movq $string9, %rbx
     # func=printf nargs=1
     # Move values from reg stack to reg args
	movq %rbx, %rdi
	movl    $0, %eax
	call printf
	movq %rax, %rbx
	#Push Local var a
	movq 120(%rsp), %rbx

	# push 0
	movq $0,%r10
	#Push Local var n
	movq 128(%rsp), %r13

	# push 1
	movq $1,%r14

	# -
	subq %r14,%r13
     # func=printArray nargs=3
     # Move values from reg stack to reg args
	movq %r13, %rdx
	movq %r10, %rsi
	movq %rbx, %rdi
	call printArray
	movq %rax, %rbx
	addq $128,%rsp
# Restore registers
	popq %r15
	popq %r14
	popq %r13
	popq %r10
	popq %rbx
	ret
string0:
	.string "%d: %s\n"

string1:
	.string "==%s==\n"

string2:
	.string "Rachael"

string3:
	.string "Monica"

string4:
	.string "Phoebe"

string5:
	.string "Joey"

string6:
	.string "Ross"

string7:
	.string "Chandler"

string8:
	.string "-------- Before -------\n"

string9:
	.string "-------- After -------\n"

