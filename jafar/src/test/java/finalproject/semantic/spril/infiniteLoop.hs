import Sprockell


prog0 :: [Instruction]
prog0 = [
    Load (ImmValue 6) regF 
    , Jump (Abs 2) 
    , Nop 
    , Load (ImmValue 3) regA 
    , Push regA 
    , Load (ImmValue 1) regA 
    , Push regA 
    , Load (ImmValue 3) regA 
    , Push regA 
    , Load (ImmValue 4) regA 
    , Push regA 
    , Load (ImmValue 7) regA 
    , Push regA 
    , Load (ImmValue 4) regD 
    , Compute Add regD regSP regD 
    , Load (IndAddr regD) regD 
    , Pop regA 
    , Load (ImmValue 3) regB 
    , Compute Add regB regD regB 
    , Store regA (IndAddr regB) 
    , Pop regA 
    , Load (ImmValue 2) regB 
    , Compute Add regB regD regB 
    , Store regA (IndAddr regB) 
    , Pop regA 
    , Load (ImmValue 1) regB 
    , Compute Add regB regD regB 
    , Store regA (IndAddr regB) 
    , Pop regA 
    , Load (ImmValue 0) regB 
    , Compute Add regB regD regB 
    , Store regA (IndAddr regB) 
    , Pop regD 
    , Load (ImmValue 1) regA 
    , Push regA 
    , Load (ImmValue 0) regA 
    , Push regA 
    , Pop regA 
    , Pop regD 
    , Store regA (IndAddr regD) 
    , Load (ImmValue 2) regA 
    , Push regA 
    , Load (ImmValue 0) regA 
    , Push regA 
    , Pop regA 
    , Pop regD 
    , Store regA (IndAddr regD) 
    , Jump (Abs 80) 
    , Nop 
    , Load (ImmValue 2) regA 
    , Push regA 
    , Load (DirAddr 2) regA 
    , Push regA 
    , Load (ImmValue 0) regD 
    , Push regD 
    , Load (DirAddr 1) regA 
    , Push regA 
    , Pop regA 
    , Load (ImmValue 3) regB 
    , Compute Gt regA regB regB 
    , Branch regB (Abs 97) 
    , Compute Lt regA reg0 regB 
    , Branch regB (Abs 97) 
    , Pop regD 
    , Load (ImmValue 1) regB 
    , Compute Mul regB regA regA 
    , Compute Add regA regD regD 
    , Push regD 
    , Pop regD 
    , Load (ImmValue 3) regA 
    , Compute Add regA regD regD 
    , Load (IndAddr regD) regD 
    , Push regD 
    , Pop regB 
    , Pop regA 
    , Compute Add regA regB regA 
    , Push regA 
    , Pop regA 
    , Pop regD 
    , Store regA (IndAddr regD) 
    , Nop 
    , Load (DirAddr 1) regA 
    , Push regA 
    , Load (ImmValue 4) regA 
    , Push regA 
    , Pop regB 
    , Pop regA 
    , Compute Lt regA regB regA 
    , Push regA 
    , Pop regA 
    , Load (ImmValue 1) regB 
    , Compute Equal regA regB regA 
    , Branch regA (Abs 48) 
    , Load (DirAddr 2) regA 
    , Push regA 
    , Pop regA 
    , WriteInstr regA numberIO 
    , EndProg 
    ]

main = run [prog0]

