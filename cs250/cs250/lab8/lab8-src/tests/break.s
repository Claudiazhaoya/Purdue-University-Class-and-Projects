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

	# push 0
	movq $0,%rbx
	#Assign to Global var i
	movq %rbx, i(%rip)

	# FOR START
twirl_start_0:
	movq i,%rbx

	# push 15
	movq $15,%r10

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
	movq i,%rbx

	# push 1
	movq $1,%r10

	# +
	addq %r10,%rbx
	#Assign to Global var i
	movq %rbx, i(%rip)
	jmp twirl_start_0
for_assign_0:
if_start_1:
	movq i,%rbx

	# push 5
	movq $5,%r10

	# ==
	cmpq %r10,%rbx
movq $0, %r10
movq $0, %rbx
movq $1, %rdi
	cmove %rdi, %rbx
	cmpq $0, %rbx
	je if_end_1
	jmp twirl_end_0

	# break above
	jmp else_end_1
if_end_1:
else_end_1:
	#top=0

	# push string "i=%d\n" top=0
	movq $string0, %rbx
	movq i,%r10
     # func=printf nargs=2
     # Move values from reg stack to reg args
	movq %r10, %rsi
	movq %rbx, %rdi
	movl    $0, %eax
	call printf
	movq %rax, %rbx
	jmp for_expression_0
twirl_end_0:

	# FOR END
	#top=0

	# push string "for i=%d\n" top=0
	movq $string1, %rbx
	movq i,%r10
     # func=printf nargs=2
     # Move values from reg stack to reg args
	movq %r10, %rsi
	movq %rbx, %rdi
	movl    $0, %eax
	call printf
	movq %rax, %rbx

	# push 0
	movq $0,%rbx
	#Assign to Global var i
	movq %rbx, i(%rip)

	# WHILE STart
twirl_start_2:
continue_point_1:
	movq i,%rbx

	# push 15
	movq $15,%r10

	# less
	cmpq %r10,%rbx
movq $0, %r10
movq $0, %rbx
movq $1, %rdi
	cmovl %rdi, %rbx
	cmpq $0, %rbx
	je twirl_end_2
	#top=0

	# push string "i=%d\n" top=0
	movq $string2, %rbx
	movq i,%r10
     # func=printf nargs=2
     # Move values from reg stack to reg args
	movq %r10, %rsi
	movq %rbx, %rdi
	movl    $0, %eax
	call printf
	movq %rax, %rbx
if_start_3:
	movq i,%rbx

	# push 8
	movq $8,%r10

	# ==
	cmpq %r10,%rbx
movq $0, %r10
movq $0, %rbx
movq $1, %rdi
	cmove %rdi, %rbx
	cmpq $0, %rbx
	je if_end_3
	jmp twirl_end_2

	# break above
	jmp else_end_3
if_end_3:
else_end_3:
	movq i,%rbx

	# push 1
	movq $1,%r10

	# +
	addq %r10,%rbx
	#Assign to Global var i
	movq %rbx, i(%rip)
	jmp twirl_start_2
twirl_end_2:

	# WHILE END
	#top=0

	# push string "while i=%d\n" top=0
	movq $string3, %rbx
	movq i,%r10
     # func=printf nargs=2
     # Move values from reg stack to reg args
	movq %r10, %rsi
	movq %rbx, %rdi
	movl    $0, %eax
	call printf
	movq %rax, %rbx

	# push 0
	movq $0,%rbx
	#Assign to Global var i
	movq %rbx, i(%rip)

	# Do start
twirl_start_4:
continue_point_2:
	#top=0

	# push string "i=%d\n" top=0
	movq $string4, %rbx
	movq i,%r10
     # func=printf nargs=2
     # Move values from reg stack to reg args
	movq %r10, %rsi
	movq %rbx, %rdi
	movl    $0, %eax
	call printf
	movq %rax, %rbx
if_start_5:
	movq i,%rbx

	# push 10
	movq $10,%r10

	# ==
	cmpq %r10,%rbx
movq $0, %r10
movq $0, %rbx
movq $1, %rdi
	cmove %rdi, %rbx
	cmpq $0, %rbx
	je if_end_5
	jmp twirl_end_4

	# break above
	jmp else_end_5
if_end_5:
else_end_5:
	movq i,%rbx

	# push 1
	movq $1,%r10

	# +
	addq %r10,%rbx
	#Assign to Global var i
	movq %rbx, i(%rip)
	movq i,%rbx

	# push 15
	movq $15,%r10

	# less
	cmpq %r10,%rbx
movq $0, %r10
movq $0, %rbx
movq $1, %rdi
	cmovl %rdi, %rbx
	cmpq $1, %rbx
	je twirl_start_4
twirl_end_4:

	# DO END
	#top=0

	# push string "do/while i=%d\n" top=0
	movq $string5, %rbx
	movq i,%r10
     # func=printf nargs=2
     # Move values from reg stack to reg args
	movq %r10, %rsi
	movq %rbx, %rdi
	movl    $0, %eax
	call printf
	movq %rax, %rbx
	#top=0

	# push string "OK\n" top=0
	movq $string6, %rbx
     # func=printf nargs=1
     # Move values from reg stack to reg args
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
	.string "i=%d\n"

string1:
	.string "for i=%d\n"

string2:
	.string "i=%d\n"

string3:
	.string "while i=%d\n"

string4:
	.string "i=%d\n"

string5:
	.string "do/while i=%d\n"

string6:
	.string "OK\n"

