	.text
.globl mysort
mysort:
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
	#Push Local var n
	movq 128(%rsp), %rbx

	# push 1
	movq $1,%r10

	# -
	subq %r10,%rbx
	#Assign to Local var i
	movq %rbx, 112(%rsp)

	# FOR START
twirl_start_0:
	#Push Local var i
	movq 112(%rsp), %rbx

	# push 0
	movq $0,%r10

	# Great
	cmpq %r10,%rbx
movq $0, %r10
movq $0, %rbx
movq $1, %rdi
	cmovg %rdi, %rbx
	cmpq $0, %rbx
	jle twirl_end_0
	jmp for_assign_0
for_expression_0:
continue_point_0:
	#Push Local var i
	movq 112(%rsp), %rbx

	# push 1
	movq $1,%r10

	# -
	subq %r10,%rbx
	#Assign to Local var i
	movq %rbx, 112(%rsp)
	jmp twirl_start_0
for_assign_0:

	# push 0
	movq $0,%rbx
	#Assign to Local var j
	movq %rbx, 104(%rsp)

	# FOR START
twirl_start_1:
	#Push Local var j
	movq 104(%rsp), %rbx
	#Push Local var i
	movq 112(%rsp), %r10

	# less
	cmpq %r10,%rbx
movq $0, %r10
movq $0, %rbx
movq $1, %rdi
	cmovl %rdi, %rbx
	cmpq $0, %rbx
	jle twirl_end_1
	jmp for_assign_1
for_expression_1:
continue_point_1:
	#Push Local var j
	movq 104(%rsp), %rbx

	# push 1
	movq $1,%r10

	# +
	addq %r10,%rbx
	#Assign to Local var j
	movq %rbx, 104(%rsp)
	jmp twirl_start_1
for_assign_1:
if_start_2:
	#Push Local var j
	movq 104(%rsp), %rbx
	#Assign2 to Local var a
			 ### type 8
	movq %rbx, %rax
	imulq $8, %rax
	addq 120(%rsp), %rax
	movq (%rax), %rbx
	#Push Local var j
	movq 104(%rsp), %r10

	# push 1
	movq $1,%r13

	# +
	addq %r13,%r10
	#Assign2 to Local var a
			 ### type 8
	movq %r10, %rax
	imulq $8, %rax
	addq 120(%rsp), %rax
	movq (%rax), %r10

	# Great
	cmpq %r10,%rbx
movq $0, %r10
movq $0, %rbx
movq $1, %rdi
	cmovg %rdi, %rbx
	cmpq $0, %rbx
	je if_end_2
	#Push Local var j
	movq 104(%rsp), %rbx
	#Assign2 to Local var a
			 ### type 8
	movq %rbx, %rax
	imulq $8, %rax
	addq 120(%rsp), %rax
	movq (%rax), %rbx
	#Assign to Local var tmp
	movq %rbx, 96(%rsp)
	#Push Local var j
	movq 104(%rsp), %rbx
	#Push Local var j
	movq 104(%rsp), %r10

	# push 1
	movq $1,%r13

	# +
	addq %r13,%r10
	#Assign2 to Local var a
			 ### type 8
	movq %r10, %rax
	imulq $8, %rax
	addq 120(%rsp), %rax
	movq (%rax), %r10
	#Assign to Local var check heere 12 a
			 ### type 120
	movq %rbx, %rax
	imulq $8, %rax
	addq 120(%rsp), %rax
	movq %r10, (%rax)
	#Push Local var j
	movq 104(%rsp), %rbx

	# push 1
	movq $1,%r10

	# +
	addq %r10,%rbx
	#Push Local var tmp
	movq 96(%rsp), %r10
	#Assign to Local var check heere 12 a
			 ### type 120
	movq %rbx, %rax
	imulq $8, %rax
	addq 120(%rsp), %rax
	movq %r10, (%rax)
	jmp else_end_2
if_end_2:
else_end_2:
	jmp for_expression_1
twirl_end_1:

	# FOR END
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
	#top=0

	# push string "----------- %s -----------\n" top=0
	movq $string0, %rbx
	#Push Local var s
	movq 128(%rsp), %r10
     # func=printf nargs=2
     # Move values from reg stack to reg args
	movq %r10, %rsi
	movq %rbx, %rdi
	movl    $0, %eax
	call printf
	movq %rax, %rbx

	# push 0
	movq $0,%rbx
	#Assign to Local var i
	movq %rbx, 104(%rsp)

	# FOR START
twirl_start_3:
	#Push Local var i
	movq 104(%rsp), %rbx
	#Push Local var n
	movq 120(%rsp), %r10

	# less
	cmpq %r10,%rbx
movq $0, %r10
movq $0, %rbx
movq $1, %rdi
	cmovl %rdi, %rbx
	cmpq $0, %rbx
	jle twirl_end_3
	jmp for_assign_3
for_expression_3:
continue_point_2:
	#Push Local var i
	movq 104(%rsp), %rbx

	# push 1
	movq $1,%r10

	# +
	addq %r10,%rbx
	#Assign to Local var i
	movq %rbx, 104(%rsp)
	jmp twirl_start_3
for_assign_3:
	#top=0

	# push string "%d\n" top=0
	movq $string1, %rbx
	#Push Local var i
	movq 104(%rsp), %r10
	#Assign2 to Local var a
			 ### type 8
	movq %r10, %rax
	imulq $8, %rax
	addq 112(%rsp), %rax
	movq (%rax), %r10
     # func=printf nargs=2
     # Move values from reg stack to reg args
	movq %r10, %rsi
	movq %rbx, %rdi
	movl    $0, %eax
	call printf
	movq %rax, %rbx
	jmp for_expression_3
twirl_end_3:

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

	# push 10
	movq $10,%rbx
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

	# push 8
	movq $8,%r10
	#Assign to Local var check heere 12 a
			 ### type 120
	movq %rbx, %rax
	imulq $8, %rax
	addq 120(%rsp), %rax
	movq %r10, (%rax)

	# push 1
	movq $1,%rbx

	# push 7
	movq $7,%r10
	#Assign to Local var check heere 12 a
			 ### type 120
	movq %rbx, %rax
	imulq $8, %rax
	addq 120(%rsp), %rax
	movq %r10, (%rax)

	# push 2
	movq $2,%rbx

	# push 1
	movq $1,%r10
	#Assign to Local var check heere 12 a
			 ### type 120
	movq %rbx, %rax
	imulq $8, %rax
	addq 120(%rsp), %rax
	movq %r10, (%rax)

	# push 3
	movq $3,%rbx

	# push 9
	movq $9,%r10
	#Assign to Local var check heere 12 a
			 ### type 120
	movq %rbx, %rax
	imulq $8, %rax
	addq 120(%rsp), %rax
	movq %r10, (%rax)

	# push 4
	movq $4,%rbx

	# push 11
	movq $11,%r10
	#Assign to Local var check heere 12 a
			 ### type 120
	movq %rbx, %rax
	imulq $8, %rax
	addq 120(%rsp), %rax
	movq %r10, (%rax)

	# push 5
	movq $5,%rbx

	# push 83
	movq $83,%r10
	#Assign to Local var check heere 12 a
			 ### type 120
	movq %rbx, %rax
	imulq $8, %rax
	addq 120(%rsp), %rax
	movq %r10, (%rax)

	# push 6
	movq $6,%rbx

	# push 7
	movq $7,%r10
	#Assign to Local var check heere 12 a
			 ### type 120
	movq %rbx, %rax
	imulq $8, %rax
	addq 120(%rsp), %rax
	movq %r10, (%rax)

	# push 7
	movq $7,%rbx

	# push 13
	movq $13,%r10
	#Assign to Local var check heere 12 a
			 ### type 120
	movq %rbx, %rax
	imulq $8, %rax
	addq 120(%rsp), %rax
	movq %r10, (%rax)

	# push 8
	movq $8,%rbx

	# push 94
	movq $94,%r10
	#Assign to Local var check heere 12 a
			 ### type 120
	movq %rbx, %rax
	imulq $8, %rax
	addq 120(%rsp), %rax
	movq %r10, (%rax)

	# push 9
	movq $9,%rbx

	# push 1
	movq $1,%r10
	#Assign to Local var check heere 12 a
			 ### type 120
	movq %rbx, %rax
	imulq $8, %rax
	addq 120(%rsp), %rax
	movq %r10, (%rax)
	#top=0

	# push string "Before" top=0
	movq $string2, %rbx
	#Push Local var n
	movq 128(%rsp), %r10
	#Push Local var a
	movq 120(%rsp), %r13
     # func=printArray nargs=3
     # Move values from reg stack to reg args
	movq %r13, %rdx
	movq %r10, %rsi
	movq %rbx, %rdi
	call printArray
	movq %rax, %rbx
	#Push Local var n
	movq 128(%rsp), %rbx
	#Push Local var a
	movq 120(%rsp), %r10
     # func=mysort nargs=2
     # Move values from reg stack to reg args
	movq %r10, %rsi
	movq %rbx, %rdi
	call mysort
	movq %rax, %rbx
	#top=0

	# push string "After" top=0
	movq $string3, %rbx
	#Push Local var n
	movq 128(%rsp), %r10
	#Push Local var a
	movq 120(%rsp), %r13
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
	.string "----------- %s -----------\n"

string1:
	.string "%d\n"

string2:
	.string "Before"

string3:
	.string "After"

