 # Reserve space
	.data
g:
	.long 0
	.long 0

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

	# push 20
	movq $20,%rbx
     # func=malloc nargs=1
     # Move values from reg stack to reg args
	movq %rbx, %rdi
	call malloc
	movq %rax, %rbx
	#Assign to Local var h
	movq %rbx, 128(%rsp)

	# push 0
	movq $0,%rbx

	# push 65
	movq $65,%r10
	#Assign to Local var check heere 12 h
			 ### type 128
	movq %rbx, %rax
	imulq $1, %rax
	addq 128(%rsp), %rax
	movq %r10, %rcx
	movb %cl, (%rax)
	movq $0, %rcx

	# push 1
	movq $1,%rbx

	# push 66
	movq $66,%r10
	#Assign to Local var check heere 12 h
			 ### type 128
	movq %rbx, %rax
	imulq $1, %rax
	addq 128(%rsp), %rax
	movq %r10, %rcx
	movb %cl, (%rax)
	movq $0, %rcx

	# push 2
	movq $2,%rbx

	# push 67
	movq $67,%r10
	#Assign to Local var check heere 12 h
			 ### type 128
	movq %rbx, %rax
	imulq $1, %rax
	addq 128(%rsp), %rax
	movq %r10, %rcx
	movb %cl, (%rax)
	movq $0, %rcx

	# push 3
	movq $3,%rbx

	# push 0
	movq $0,%r10
	#Assign to Local var check heere 12 h
			 ### type 128
	movq %rbx, %rax
	imulq $1, %rax
	addq 128(%rsp), %rax
	movq %r10, %rcx
	movb %cl, (%rax)
	movq $0, %rcx
	#top=0

	# push string "h=%s\n" top=0
	movq $string0, %rbx
	#Push Local var h
	movq 128(%rsp), %r10
     # func=printf nargs=2
     # Move values from reg stack to reg args
	movq %r10, %rsi
	movq %rbx, %rdi
	movl    $0, %eax
	call printf
	movq %rax, %rbx

	# push 20
	movq $20,%rbx
     # func=malloc nargs=1
     # Move values from reg stack to reg args
	movq %rbx, %rdi
	call malloc
	movq %rax, %rbx
	#Assign to Global var g
	movq %rbx, g(%rip)

	# push 0
	movq $0,%rbx

	# push 68
	movq $68,%r10
		 # REG[top-1]: r10, REG[top-2]: rbx
	#Assign to Global check here var g
	movq g(%rip), %rax
	movq %rbx, %r12
			 ### global_vars_type[i]:$1
	imulq $1, %rbx
	addq %rbx, %rax
	movq %r12, %rbx
	movq %r10, (%rax)

	# push 1
	movq $1,%rbx

	# push 69
	movq $69,%r10
		 # REG[top-1]: r10, REG[top-2]: rbx
	#Assign to Global check here var g
	movq g(%rip), %rax
	movq %rbx, %r12
			 ### global_vars_type[i]:$1
	imulq $1, %rbx
	addq %rbx, %rax
	movq %r12, %rbx
	movq %r10, (%rax)

	# push 2
	movq $2,%rbx

	# push 70
	movq $70,%r10
		 # REG[top-1]: r10, REG[top-2]: rbx
	#Assign to Global check here var g
	movq g(%rip), %rax
	movq %rbx, %r12
			 ### global_vars_type[i]:$1
	imulq $1, %rbx
	addq %rbx, %rax
	movq %r12, %rbx
	movq %r10, (%rax)

	# push 3
	movq $3,%rbx

	# push 0
	movq $0,%r10
		 # REG[top-1]: r10, REG[top-2]: rbx
	#Assign to Global check here var g
	movq g(%rip), %rax
	movq %rbx, %r12
			 ### global_vars_type[i]:$1
	imulq $1, %rbx
	addq %rbx, %rax
	movq %r12, %rbx
	movq %r10, (%rax)
	#top=0

	# push string "g=%s\n" top=0
	movq $string1, %rbx
	movq g,%r10
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
	.string "h=%s\n"

string1:
	.string "g=%s\n"

