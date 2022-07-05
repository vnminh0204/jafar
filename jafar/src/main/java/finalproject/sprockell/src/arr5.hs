import Sprockell


prog0 :: [Instruction]
prog0 = [
    Load (ImmValue 7) regF 
    , Jump (Abs 2) 
    , Nop 
    , Load (ImmValue 1) regA 
    , Push regA 
    , Load (ImmValue 1) regA 
    , Push regA 
    , Load (ImmValue 3) regA 
    , Push regA 
    , Load (ImmValue 4) regA 
    , Push regA 
    , Load (ImmValue 3) regD 
    , Compute Add regD regSP regD 
    , Load (IndAddr regD) regD 
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
    , Load (ImmValue 4) regA 
    , Push regA 
    , Load (ImmValue 1) regA 
    , Push regA 
    , Load (ImmValue 4) regA 
    , Push regA 
    , Load (ImmValue 4) regA 
    , Push regA 
    , Load (ImmValue 3) regD 
    , Compute Add regD regSP regD 
    , Load (IndAddr regD) regD 
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
    , Load (ImmValue 0) regE 
    , Push regE 
    , Load (ImmValue 1) regA 
    , Push regA 
    , Pop regA 
    , Pop regE 
    , Load (ImmValue 1) regB 
    , Compute Mul regB regA regA 
    , Compute Add regA regE regE 
    , Push regE 
    , Load (ImmValue 4) regA 
    , Pop regE 
    , Compute Add regA regE regE 
    , Push regE 
    , Load (ImmValue 0) regD 
    , Load (ImmValue 1) regA 
    , Push regA 
    , Pop regA 
    , Load (ImmValue 1) regB 
    , Compute Mul regB regA regA 
    , Compute Add regA regD regD 
    , Load (ImmValue 4) regA 
    , Compute Add regA regD regD 
    , Load (IndAddr regD) regD 
    , Push regD 
    , Load (ImmValue 1) regA 
    , Push regA 
    , Pop regB 
    , Pop regA 
    , Compute Sub regA regB regA 
    , Push regA 
    , Pop regA 
    , Pop regD 
    , Store regA (IndAddr regD) 
    , Load (ImmValue 7) regA 
    , Push regA 
    , Load (ImmValue 1) regA 
    , Push regA 
    , Load (ImmValue 4) regA 
    , Push regA 
    , Pop regB 
    , Pop regA 
    , Load (ImmValue 1) regC 
    , Load (IndAddr regA) regD 
    , Load (IndAddr regB) regE 
    , Compute Equal regD regE regD 
    , Compute And regC regD regC 
    , Load (ImmValue 1) regD 
    , Compute Add regA regD regA 
    , Compute Add regB regD regB 
    , Load (IndAddr regA) regD 
    , Load (IndAddr regB) regE 
    , Compute Equal regD regE regD 
    , Compute And regC regD regC 
    , Load (ImmValue 1) regD 
    , Compute Add regA regD regA 
    , Compute Add regB regD regB 
    , Load (IndAddr regA) regD 
    , Load (IndAddr regB) regE 
    , Compute Equal regD regE regD 
    , Compute And regC regD regC 
    , Load (ImmValue 1) regD 
    , Compute Add regA regD regA 
    , Compute Add regB regD regB 
    , Push regC 
    , Pop regA 
    , Pop regD 
    , Store regA (IndAddr regD) 
    , Load (DirAddr 7) regA 
    , Push regA 
    , Pop regA 
    , WriteInstr regA numberIO 
    , EndProg 
    ]

main = run [prog0]

