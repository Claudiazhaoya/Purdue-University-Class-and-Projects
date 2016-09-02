	.text
.globl max
max:
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
	#Assign2 to Local var a
			 ### type 8
	movq %rbx, %rax
	imulq $8, %rax
	addq 128(%rsp), %rax
	movq (%rax), %rbx
	#Assign to Local var m
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
	movq 120(%rsp), %r10

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
if_start_1:
	#Push Local var m
	movq 104(%rsp), %rbx
	#Push Local var i
	movq 112(%rsp), %r10
	#Assign2 to Local var a
			 ### type 8
	movq %r10, %rax
	imulq $8, %rax
	addq 128(%rsp), %rax
	movq (%rax), %r10

	# less
	cmpq %r10,%rbx
movq $0, %r10
movq $0, %rbx
movq $1, %rdi
	cmovl %rdi, %rbx
	cmpq $0, %rbx
	je if_end_1
	#Push Local var i
	movq 112(%rsp), %rbx
	#Assign2 to Local var a
			 ### type 8
	movq %rbx, %rax
	imulq $8, %rax
	addq 128(%rsp), %rax
	movq (%rax), %rbx
	#Assign to Local var m
	movq %rbx, 104(%rsp)
	jmp else_end_1
if_end_1:
else_end_1:
	jmp for_expression_0
twirl_end_0:

	# FOR END
	#Push Local var m
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
 # Reserve space
	.data
a:
	.long 0
	.long 0

 # Reserve space
	.data
n:
	.long 0
	.long 0

 # Reserve space
	.data
i:
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

	# push 10
	movq $10,%rbx
	#Assign to Global var n
	movq %rbx, n(%rip)

	# push 10
	movq $10,%rbx

	# push 8
	movq $8,%r10

	# *
	imulq %r10,%rbx
     # func=malloc nargs=1
     # Move values from reg stack to reg args
	movq %rbx, %rdi
	call malloc
	movq %rax, %rbx
	#Assign to Global var a
	movq %rbx, a(%rip)

	# push 0
	movq $0,%rbx

	# push 8
	movq $8,%r10
		 # REG[top-1]: r10, REG[top-2]: rbx
	#Assign to Global check here var a
	movq a(%rip), %rax
	movq %rbx, %r12
			 ### global_vars_type[i]:$8
	imulq $8, %rbx
	addq %rbx, %rax
	movq %r12, %rbx
	movq %r10, (%rax)

	# push 1
	movq $1,%rbx

	# push 7
	movq $7,%r10
		 # REG[top-1]: r10, REG[top-2]: rbx
	#Assign to Global check here var a
	movq a(%rip), %rax
	movq %rbx, %r12
			 ### global_vars_type[i]:$8
	imulq $8, %rbx
	addq %rbx, %rax
	movq %r12, %rbx
	movq %r10, (%rax)

	# push 2
	movq $2,%rbx

	# push 1
	movq $1,%r10
		 # REG[top-1]: r10, REG[top-2]: rbx
	#Assign to Global check here var a
	movq a(%rip), %rax
	movq %rbx, %r12
			 ### global_vars_type[i]:$8
	imulq $8, %rbx
	addq %rbx, %rax
	movq %r12, %rbx
	movq %r10, (%rax)

	# push 3
	movq $3,%rbx

	# push 9
	movq $9,%r10
		 # REG[top-1]: r10, REG[top-2]: rbx
	#Assign to Global check here var a
	movq a(%rip), %rax
	movq %rbx, %r12
			 ### global_vars_type[i]:$8
	imulq $8, %rbx
	addq %rbx, %rax
	movq %r12, %rbx
	movq %r10, (%rax)

	# push 4
	movq $4,%rbx

	# push 11
	movq $11,%r10
		 # REG[top-1]: r10, REG[top-2]: rbx
	#Assign to Global check here var a
	movq a(%rip), %rax
	movq %rbx, %r12
			 ### global_vars_type[i]:$8
	imulq $8, %rbx
	addq %rbx, %rax
	movq %r12, %rbx
	movq %r10, (%rax)

	# push 5
	movq $5,%rbx

	# push 83
	movq $83,%r10
		 # REG[top-1]: r10, REG[top-2]: rbx
	#Assign to Global check here var a
	movq a(%rip), %rax
	movq %rbx, %r12
			 ### global_vars_type[i]:$8
	imulq $8, %rbx
	addq %rbx, %rax
	movq %r12, %rbx
	movq %r10, (%rax)

	# push 6
	movq $6,%rbx

	# push 7
	movq $7,%r10
		 # REG[top-1]: r10, REG[top-2]: rbx
	#Assign to Global check here var a
	movq a(%rip), %rax
	movq %rbx, %r12
			 ### global_vars_type[i]:$8
	imulq $8, %rbx
	addq %rbx, %rax
	movq %r12, %rbx
	movq %r10, (%rax)

	# push 7
	movq $7,%rbx

	# push 13
	movq $13,%r10
		 # REG[top-1]: r10, REG[top-2]: rbx
	#Assign to Global check here var a
	movq a(%rip), %rax
	movq %rbx, %r12
			 ### global_vars_type[i]:$8
	imulq $8, %rbx
	addq %rbx, %rax
	movq %r12, %rbx
	movq %r10, (%rax)

	# push 8
	movq $8,%rbx

	# push 94
	movq $94,%r10
		 # REG[top-1]: r10, REG[top-2]: rbx
	#Assign to Global check here var a
	movq a(%rip), %rax
	movq %rbx, %r12
			 ### global_vars_type[i]:$8
	imulq $8, %rbx
	addq %rbx, %rax
	movq %r12, %rbx
	movq %r10, (%rax)

	# push 9
	movq $9,%rbx

	# push 1
	movq $1,%r10
		 # REG[top-1]: r10, REG[top-2]: rbx
	#Assign to Global check here var a
	movq a(%rip), %rax
	movq %rbx, %r12
			 ### global_vars_type[i]:$8
	imulq $8, %rbx
	addq %rbx, %rax
	movq %r12, %rbx
	movq %r10, (%rax)

	# push 0
	movq $0,%rbx
	#Assign to Global var i
	movq %rbx, i(%rip)

	# FOR START
twirl_start_2:
	movq i,%rbx
	movq n,%r10

	# less
	cmpq %r10,%rbx
movq $0, %r10
movq $0, %rbx
movq $1, %rdi
	cmovl %rdi, %rbx
	cmpq $0, %rbx
	jle twirl_end_2
	jmp for_assign_2
for_expression_2:
continue_point_1:
	movq i,%rbx

	# push 1
	movq $1,%r10

	# +
	addq %r10,%rbx
	#Assign to Global var i
	movq %rbx, i(%rip)
	jmp twirl_start_2
for_assign_2:
	#top=0

	# push string "%d: %d\n" top=0
	movq $string0, %rbx
	movq i,%r10
	movq i,%r13
	#Assign2 to Global var a
	movq a(%rip), %rax
	movq %r13, %r12
	imulq $8, %r13
	addq %r13, %rax
	movq (%rax), %r13
	movq $0, %r12
     # func=printf nargs=3
     # Move values from reg stack to reg args
	movq %r13, %rdx
	movq %r10, %rsi
	movq %rbx, %rdi
	movl    $0, %eax
	call printf
	movq %rax, %rbx
	jmp for_expression_2
twirl_end_2:

	# FOR END
	#top=0

	# push string "n=%d\n" top=0
	movq $string1, %rbx
	movq n,%r10
     # func=printf nargs=2
     # Move values from reg stack to reg args
	movq %r10, %rsi
	movq %rbx, %rdi
	movl    $0, %eax
	call printf
	movq %rax, %rbx
	#top=0

	# push string "max=%d\n" top=0
	movq $string2, %rbx
	movq a,%r10
	movq n,%r13
     # func=max nargs=2
     # Move values from reg stack to reg args
	movq %r13, %rsi
	movq %r10, %rdi
	call max
	movq %rax, %r10
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
	.string "%d: %d\n"

string1:
	.string "n=%d\n"

string2:
	.string "max=%d\n"

