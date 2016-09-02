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

	# push 8
	movq $8,%rbx

	# push 20
	movq $20,%r10

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
	#Assign to Local var i
	movq %rbx, 120(%rsp)

	# FOR START
twirl_start_0:
	#Push Local var i
	movq 120(%rsp), %rbx

	# push 20
	movq $20,%r10

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
	movq 120(%rsp), %rbx

	# push 1
	movq $1,%r10

	# +
	addq %r10,%rbx
	#Assign to Local var i
	movq %rbx, 120(%rsp)
	jmp twirl_start_0
for_assign_0:
	#Push Local var i
	movq 120(%rsp), %rbx

	# push 3
	movq $3,%r10
	#Push Local var i
	movq 120(%rsp), %r13

	# *
	imulq %r13,%r10
	#Assign to Local var check heere 12 a
			 ### type 128
	movq %rbx, %rax
	imulq $8, %rax
	addq 128(%rsp), %rax
	movq %r10, (%rax)
	jmp for_expression_0
twirl_end_0:

	# FOR END
	#top=0

	# push string "Ok so far\n" top=0
	movq $string0, %rbx
     # func=printf nargs=1
     # Move values from reg stack to reg args
	movq %rbx, %rdi
	movl    $0, %eax
	call printf
	movq %rax, %rbx

	# push 0
	movq $0,%rbx
	#Assign to Local var i
	movq %rbx, 120(%rsp)

	# FOR START
twirl_start_1:
	#Push Local var i
	movq 120(%rsp), %rbx

	# push 20
	movq $20,%r10

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
	#Push Local var i
	movq 120(%rsp), %rbx

	# push 1
	movq $1,%r10

	# +
	addq %r10,%rbx
	#Assign to Local var i
	movq %rbx, 120(%rsp)
	jmp twirl_start_1
for_assign_1:
	#top=0

	# push string "%d: %d\n" top=0
	movq $string1, %rbx
	#Push Local var i
	movq 120(%rsp), %r10
	#Push Local var i
	movq 120(%rsp), %r13
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
	jmp for_expression_1
twirl_end_1:

	# FOR END
	addq $128,%rsp
# Restore registers
	popq %r15
	popq %r14
	popq %r13
	popq %r10
	popq %rbx
	ret
string0:
	.string "Ok so far\n"

string1:
	.string "%d: %d\n"

