	.text
.globl sum
sum:
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

	# push 0
	movq $0,%rbx
	#Assign to Local var s
	movq %rbx, 104(%rsp)

	# push 0
	movq $0,%rbx
	#Assign to Local var i
	movq %rbx, 112(%rsp)

	# FOR START
twirl_start_0:
	#Push Local var i
	movq 112(%rsp), %rbx
	#Push Local var n
	movq 128(%rsp), %r10

	# less
	cmpq %r10,%rbx
movq $0, %r10
movq $0, %rbx
movq $1, %rdi
	cmovl %rdi, %rbx
	cmpq $0, %rbx
	jle twirl_end_0
	jmp for_assign_0
for_expression_0:
continue_point_0:
	#Push Local var i
	movq 112(%rsp), %rbx

	# push 1
	movq $1,%r10

	# +
	addq %r10,%rbx
	#Assign to Local var i
	movq %rbx, 112(%rsp)
	jmp twirl_start_0
for_assign_0:
	#Push Local var s
	movq 104(%rsp), %rbx
	#Push Local var i
	movq 112(%rsp), %r10
	#Assign2 to Local var a
			 ### type 8
	movq %r10, %rax
	imulq $8, %rax
	addq 120(%rsp), %rax
	movq (%rax), %r10

	# +
	addq %r10,%rbx
	#Assign to Local var s
	movq %rbx, 104(%rsp)
	jmp for_expression_0
twirl_end_0:

	# FOR END
	#Push Local var s
	movq 104(%rsp), %rbx
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

	# push 5
	movq $5,%rbx

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
	movq %rbx, 128(%rsp)

	# push 0
	movq $0,%rbx

	# push 4
	movq $4,%r10
	#Assign to Local var check heere 12 a
			 ### type 128
	movq %rbx, %rax
	imulq $8, %rax
	addq 128(%rsp), %rax
	movq %r10, (%rax)

	# push 1
	movq $1,%rbx

	# push 3
	movq $3,%r10
	#Assign to Local var check heere 12 a
			 ### type 128
	movq %rbx, %rax
	imulq $8, %rax
	addq 128(%rsp), %rax
	movq %r10, (%rax)

	# push 2
	movq $2,%rbx

	# push 1
	movq $1,%r10
	#Assign to Local var check heere 12 a
			 ### type 128
	movq %rbx, %rax
	imulq $8, %rax
	addq 128(%rsp), %rax
	movq %r10, (%rax)

	# push 3
	movq $3,%rbx

	# push 7
	movq $7,%r10
	#Assign to Local var check heere 12 a
			 ### type 128
	movq %rbx, %rax
	imulq $8, %rax
	addq 128(%rsp), %rax
	movq %r10, (%rax)

	# push 4
	movq $4,%rbx

	# push 6
	movq $6,%r10
	#Assign to Local var check heere 12 a
			 ### type 128
	movq %rbx, %rax
	imulq $8, %rax
	addq 128(%rsp), %rax
	movq %r10, (%rax)

	# push 5
	movq $5,%rbx
	#Push Local var a
	movq 128(%rsp), %r10
     # func=sum nargs=2
     # Move values from reg stack to reg args
	movq %r10, %rsi
	movq %rbx, %rdi
	call sum
	movq %rax, %rbx
	#Assign to Local var s
	movq %rbx, 120(%rsp)
	#top=0

	# push string "sum=%d\n" top=0
	movq $string0, %rbx
	#Push Local var s
	movq 120(%rsp), %r10
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
string0:
	.string "sum=%d\n"

